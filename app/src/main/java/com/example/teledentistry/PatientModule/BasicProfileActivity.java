package com.example.teledentistry.PatientModule;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.DoctorModule.Calender_and_Schedule_Activity;
import com.example.teledentistry.DoctorModule.Doc_profile_activity1;
import com.example.teledentistry.DoctorModule.Doc_profile_activity2;
import com.example.teledentistry.DoctorModule.Doc_profile_activity3;
import com.example.teledentistry.DoctorModule.LoginActivity_Doc_Module;
import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class BasicProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    private static  final String TAG="BasicProfileActivity";
    TextView Date,age_tv2;
   // int year;
    int month;
    int day;

    private Bitmap bitmap;
    Button next;
    String fullName, email, password, cpassword;
    String txt_gender, txt_dob, txt_maritalStatus, txt_feet, txt_inches, txt_bloodGroup, txt_weight,txt_age;
    FirebaseAuth firebaseAuth;
    private StorageReference referenceStorage = FirebaseStorage.getInstance().getReference("Images");
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_profile_pat_module);
        Date=findViewById(R.id.dob);
        age_tv2 = findViewById(R.id.age_tv2);
        next = findViewById(R.id.next);

        firebaseAuth = FirebaseAuth.getInstance();


        final Calendar cal=Calendar.getInstance();

        final Spinner gender_spinner=findViewById(R.id.spinner);
        gender_spinner.setOnItemSelectedListener(this);

        final Spinner maritalStatus_spinner=findViewById(R.id.spinner2);
        maritalStatus_spinner.setOnItemSelectedListener(this);

        final Spinner feet_spinner=findViewById(R.id.spinner3);
        feet_spinner.setOnItemSelectedListener(this);

        final Spinner inches_spinner=findViewById(R.id.spinner4);
        inches_spinner.setOnItemSelectedListener(this);

        final Spinner bloodGroup_spinner=findViewById(R.id.spinner5);
        bloodGroup_spinner.setOnItemSelectedListener(this);

        final Spinner weight_spinner=findViewById(R.id.spinner6);
        weight_spinner.setOnItemSelectedListener(this);

        ProfileImage = findViewById(R.id.profile_image);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        long january = calendar.getTimeInMillis();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        long december = calendar.getTimeInMillis();

        //CalenderConstraints
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setStart(january);
        constraintsBuilder.setEnd(december);
        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        //Date Picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        builder.setSelection(today);
      //  builder.setCalendarConstraints(constraintsBuilder.build());

        final MaterialDatePicker materialDatePicker = builder.build();

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                try {

                    SimpleDateFormat  simpleDateFormatYear = new SimpleDateFormat("YYYY");
                    String year = simpleDateFormatYear.format(selection);

                    int yearr = Integer.parseInt(year);

                    final Calendar cal=Calendar.getInstance();
                    int current_year=cal.get(Calendar.YEAR);

                    int age = current_year - yearr;

                    String actual_age = Integer.toString(age);

                    Date.setText( materialDatePicker.getHeaderText() );
                    age_tv2.setText(actual_age);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

       /* Date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                year=cal.get(Calendar.YEAR);
                month=cal.get(Calendar.MONTH);
                 day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(BasicProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date.setText(SimpleDateFormat.getDateInstance().format(cal.getTime()));

                    }
                },year,month,day);
                dialog.show();
            }
        }); */

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_gender = gender_spinner.getSelectedItem().toString();
                txt_dob = Date.getText().toString();
                txt_maritalStatus = maritalStatus_spinner.getSelectedItem().toString();
                txt_feet = feet_spinner.getSelectedItem().toString();
                txt_inches = inches_spinner.getSelectedItem().toString();
                txt_bloodGroup = bloodGroup_spinner.getSelectedItem().toString();
                txt_weight = weight_spinner.getSelectedItem().toString();
                txt_age = age_tv2.getText().toString();



                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(BasicProfileActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }


                if(TextUtils.isEmpty(txt_gender) || TextUtils.isEmpty(txt_dob) || TextUtils.isEmpty(txt_bloodGroup) ||
                        TextUtils.isEmpty(txt_feet) || TextUtils.isEmpty(txt_inches) || TextUtils.isEmpty(txt_maritalStatus)
                        || TextUtils.isEmpty(txt_weight)
                ){
                    Toast.makeText(BasicProfileActivity.this, "All field are required",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void uploadToFirebase(Uri uri){

        Intent i = getIntent();
        fullName = i.getStringExtra("full_name");
        email = i.getStringExtra("email");
        password = i.getStringExtra("password");
        cpassword = i.getStringExtra("confirm_password");


        final StorageReference fileRef = referenceStorage.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {

                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                            final String userId = firebaseUser.getUid();
                                            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);

                                            firebaseUser.getIdToken(true)
                                                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                                                            token = task.getResult().getToken();
                                                        }
                                                    });

                                            HashMap<String, Object> hashMap = new HashMap<>();
                                            hashMap.put("full_name", fullName);
                                            hashMap.put("email", email);
                                            hashMap.put("password", password);
                                            hashMap.put("confirm_password", cpassword);
                                            hashMap.put("gender", txt_gender);
                                            hashMap.put("age",txt_age);
                                            hashMap.put("dob", txt_dob);
                                            hashMap.put("marital_status", txt_maritalStatus);
                                            hashMap.put("feet", txt_feet);
                                            hashMap.put("inches", txt_inches);
                                            hashMap.put("blood_group", txt_bloodGroup);
                                            hashMap.put("weight", txt_weight);
                                            hashMap.put("imageUrl", uri.toString());
                                            hashMap.put("user_token",token);

                                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent intent1 = new Intent(BasicProfileActivity.this, ContactInformationActivity.class);
                                                        startActivity(intent1);
                                                        finish();
                                                    }
                                                }
                                            });

                                        }
                                        else {
                                            Toast.makeText(BasicProfileActivity.this,"You cann't register with this email or password",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                        //modelId = root.push().getKey();
                        //root.setValue(model);
                        Toast.makeText(BasicProfileActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(BasicProfileActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });


    }




    /*private void uploadToFirebase(Uri imageUri) {
        final StorageReference fileRef = referenceStorage.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        final String userId = firebaseUser.getUid();
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients").push();

                        reference.child(userId)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        HashMap<String, Object> postHashMap = new HashMap<>();
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            postHashMap.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                                        }
                                        postHashMap.put("gender", txt_gender);
                                        postHashMap.put("dob", txt_dob);
                                        postHashMap.put("marital_status", txt_maritalStatus);
                                        postHashMap.put("feet", txt_feet);
                                        postHashMap.put("inches", txt_inches);
                                        postHashMap.put("blood_group", txt_bloodGroup);
                                        postHashMap.put("weight", txt_weight);
                                        postHashMap.put("imageUrl", uri.toString());

                                        reference.child(userId).updateChildren(postHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent intent1 = new Intent(BasicProfileActivity.this, ContactInformationActivity.class);
                                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent1);
                                                }
                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        Toast.makeText(BasicProfileActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(BasicProfileActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });


    }*/

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
                ProfileImage.setImageBitmap(bitmap);
            }

        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}