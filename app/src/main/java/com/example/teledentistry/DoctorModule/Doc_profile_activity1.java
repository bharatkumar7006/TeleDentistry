package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Doc_profile_activity1 extends AppCompatActivity {
    Spinner gender_spinner, maritalstatus_spinner;
    Button next1_btn;
    ConstraintLayout constraintLayout;
    TextInputLayout doc_DOB;
    FirebaseAuth firebaseAuth;
    CircleImageView doc_profile_iv;
    private static final int PICK_IMAGE=1;
    Uri imageUri;
    private Bitmap bitmap;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Doctors").push();
    private StorageReference reference = FirebaseStorage.getInstance().getReference("Images");
    String email,password,phone_no,full_name,practitioner_license,date_of_birth,gender,marital_status;
    ProgressDialog progressDialog;
    String status = "offline";
    HashMap<String,List<String>> slots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile_activity1_doc_module);

         doc_DOB = (TextInputLayout)findViewById(R.id.dob_textInputLayout);
         gender_spinner = findViewById(R.id.gender_spinner);
         maritalstatus_spinner = findViewById(R.id.maritalstatus_spinner);
         next1_btn = (Button) findViewById(R.id.next1);
         constraintLayout = findViewById(R.id.doc_profile_activity1_layout);
         doc_profile_iv = findViewById(R.id.doc_profile_Iv);

         progressDialog = new ProgressDialog(this);
         slots = new HashMap<>();

        ArrayAdapter<CharSequence> genderarray_Adapter = ArrayAdapter.createFromResource(this,R.array.spinner_gender_item,R.layout.color_gender_spinner_layout_doc_module);
        genderarray_Adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout_doc_module);
        gender_spinner.setAdapter(genderarray_Adapter);


        ArrayAdapter<CharSequence> maritalstatus_arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_maritalstatus_item,R.layout.color_maritalstatus_spinner_layout_doc_module);
        maritalstatus_arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout_doc_module);
        maritalstatus_spinner.setAdapter(maritalstatus_arrayAdapter);

        firebaseAuth = FirebaseAuth.getInstance();

        doc_profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        next1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = getIntent();

                 date_of_birth = doc_DOB.getEditText().getText().toString();
                gender = gender_spinner.getSelectedItem().toString();
                marital_status = maritalstatus_spinner.getSelectedItem().toString();

                 if (imageUri != null){
                   uploadToFirebase(imageUri);
                }else{
                  Toast.makeText(Doc_profile_activity1.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }


                if(TextUtils.isEmpty(date_of_birth) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(marital_status)
                ){
                    Toast.makeText(Doc_profile_activity1.this, "All field are required",Toast.LENGTH_SHORT).show();
                }
                else {
                    full_name = i.getStringExtra("fullname");
                    phone_no = i.getStringExtra("phoneno");
                    email = i.getStringExtra("email");
                    password = i.getStringExtra("password");
                    practitioner_license = i.getStringExtra("practitionerLicense");

                }
            }
        });

    }

    private void uploadToFirebase(Uri uri){

        progressDialog.setMessage("Processing Please Wait...");
        progressDialog.show();

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {
                            String imageUrl = uri.toString();
                        final DoctorModel model = new DoctorModel(date_of_birth,email,full_name,gender,imageUrl,marital_status,
                                password,phone_no,practitioner_license,status,slots);

                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                            String userId = firebaseUser.getUid();
                                            root = FirebaseDatabase.getInstance().getReference("Doctors").child(userId);

                                             root.setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent intent1 = new Intent(Doc_profile_activity1.this, Doc_profile_activity2.class);
                                                        startActivity(intent1);
                                                        finish();
                                                    }
                                                }
                                            });

                                        }
                                        else {
                                            Toast.makeText(Doc_profile_activity1.this,"You cann't register with this email or password",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        Toast.makeText(Doc_profile_activity1.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Doc_profile_activity1.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            }catch (Exception e){
                e.printStackTrace();
            }
            doc_profile_iv.setImageBitmap(bitmap);
        }

    }
}