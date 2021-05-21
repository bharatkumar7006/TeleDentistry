package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doc_profile_activity3 extends AppCompatActivity {
    Button finish_btn;
    Spinner year_experience_spinner, month_experience_spinner;
    TextInputLayout doc_speciality, doc_AreaOfExpertise, doc_ConsultationFee, doc_Qualification, doc_Hospital, doc_Award, doc_WorkAddress;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String status = "offline";
    HashMap<String,List<String>> slots;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile_activity3_doc_module);

        progressDialog = new ProgressDialog(this);
        slots = new HashMap<>();

        doc_speciality = findViewById(R.id.speciality_textInputLayout);
        doc_AreaOfExpertise = findViewById(R.id.areaofExperties_textInputLayout);
        doc_ConsultationFee = findViewById(R.id.consultationFee_textInputLayout);
        doc_Qualification = findViewById(R.id.qualification_textInputLayout);
        doc_Hospital = findViewById(R.id.hospital_textInputLayout);
        doc_Award = findViewById(R.id.award_textInputLayout);
        doc_WorkAddress = findViewById(R.id.workAddress_textInputLayout);
        year_experience_spinner = findViewById(R.id.yearExperience_spinner);
        month_experience_spinner = findViewById(R.id.monthExperience_spinner);
        finish_btn = findViewById(R.id.finish_btn);

        ArrayAdapter yearArrayAdapter = ArrayAdapter.createFromResource(this, R.array.year_experience, R.layout.color_year_experience_spinner_layout_doc_module);
        year_experience_spinner.setAdapter(yearArrayAdapter);

        ArrayAdapter monthArrayAdapter = ArrayAdapter.createFromResource(this, R.array.monthExperience, R.layout.color_month_experience_spinner_layout_doc_module);
        month_experience_spinner.setAdapter(monthArrayAdapter);

        firebaseAuth = FirebaseAuth.getInstance();

        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDoctor();
            }
        });

    }

    private void updateDoctor() {

        final String speciality = doc_speciality.getEditText().getText().toString();
        final String expertise = doc_AreaOfExpertise.getEditText().getText().toString();
        final String consultation_fee = doc_ConsultationFee.getEditText().getText().toString();
        final String qualificaion = doc_Qualification.getEditText().getText().toString();
        final String hospital = doc_Hospital.getEditText().getText().toString();
        final String award = doc_Award.getEditText().getText().toString();
        final String work_address = doc_WorkAddress.getEditText().getText().toString();
        final String experience_year = year_experience_spinner.getSelectedItem().toString();
        final String experience_month = month_experience_spinner.getSelectedItem().toString();

        Intent i = getIntent();

        String address = i.getStringExtra("address");
        String city = i.getStringExtra("city");
        String account_no = i.getStringExtra("accountno");
        String account_type = i.getStringExtra("accountType");
        String country = i.getStringExtra("country");
        String state = i.getStringExtra("state");


        if (TextUtils.isEmpty(speciality) || TextUtils.isEmpty(expertise) || TextUtils.isEmpty(consultation_fee) ||
                TextUtils.isEmpty(qualificaion) || TextUtils.isEmpty(hospital) || TextUtils.isEmpty(award) ||
                TextUtils.isEmpty(work_address) || TextUtils.isEmpty(experience_year) || TextUtils.isEmpty(experience_month)
        ) {
            Toast.makeText(Doc_profile_activity3.this, "All field are required", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();


        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String userId = firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Doctors");

        final DoctorModel model = new DoctorModel(award,consultation_fee,experience_year,experience_month,expertise,hospital
        ,qualificaion,speciality,work_address,address,city,account_no,account_type,country,state,status,slots);

        reference.child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String, Object> postHashMap = new HashMap<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            postHashMap.put(dataSnapshot.getKey(), dataSnapshot.getValue());

                        }

                        postHashMap.put("award",model.getAward());
                        postHashMap.put("consultation_fee",model.getConsultation_fee());
                        postHashMap.put("experience_year",model.getExperience_year());
                        postHashMap.put("experience_month",model.getExperience_month());
                        postHashMap.put("expertise",model.getExpertise());
                        postHashMap.put("hospital",model.getHospital());
                        postHashMap.put("qualificaion",model.getQualificaion());
                        postHashMap.put("speciality",model.getSpeciality());
                        postHashMap.put("work_address",model.getWork_address());
                        postHashMap.put("address",model.getAddress());
                        postHashMap.put("city",model.getCity());
                        postHashMap.put("account_no",model.getAccount_no());
                        postHashMap.put("account_type",model.getAccount_type());
                        postHashMap.put("country",model.getCountry());
                        postHashMap.put("state",model.getState());


                        reference.child(userId).updateChildren(postHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent1 = new Intent(Doc_profile_activity3.this, LoginActivity_Doc_Module.class);
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

    }


}
