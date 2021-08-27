package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.LoginActivity_Doc_Module;
import com.example.teledentistry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientProfileActivity extends AppCompatActivity {
    ImageButton basicinfo, contactdetail;
    TextView patient_name_tv, email_tv, cnic_tv,bloodgp_tv,age_tv,gender_tv,maritalStatus_tv,
            height_tv,weight_tv, phone_tv,emergencyPhoneNo_tv,address_tv;
    String patient_name,bloodgp,gender,maritalStatus,height,weight,age,phoneNo,emergencyPhoneNo,address;
    CircleImageView patient_profile_iv;

    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_pat_module);
        basicinfo = findViewById(R.id.EditBasicInformation_btn);
        patient_name_tv = findViewById(R.id.patientName_tv);
        patient_profile_iv = findViewById(R.id.header_patient_profile_iv);
        email_tv = findViewById(R.id.email_tv);
        cnic_tv = findViewById(R.id.cnic_tv);
        bloodgp_tv = findViewById(R.id.bloodgp_tv);
        age_tv = findViewById(R.id.age_tv);
        gender_tv = findViewById(R.id.gender_tv);
        maritalStatus_tv = findViewById(R.id.maritalStatus_tv);
        height_tv = findViewById(R.id.height_tv);
        weight_tv = findViewById(R.id.weight_tv);
        phone_tv = findViewById(R.id.phoneNo_tv);
        emergencyPhoneNo_tv = findViewById(R.id.emergencyPhoneNo_tv);
        address_tv = findViewById(R.id.address_tv);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userId = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);

        email_tv.setText(user.getEmail());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patient_name = snapshot.child("full_name").getValue(String.class);
                bloodgp = snapshot.child("blood_group").getValue(String.class);
                age = snapshot.child("age").getValue(String.class);
                gender = snapshot.child("gender").getValue(String.class);
                maritalStatus = snapshot.child("marital_status").getValue(String.class);
                height = snapshot.child("feet").getValue(String.class);
                height = height +"'"+snapshot.child("inches").getValue(String.class);
                weight = snapshot.child("weight").getValue(String.class);
                phoneNo = snapshot.child("phone_no").getValue(String.class);
                emergencyPhoneNo = snapshot.child("emergency_no").getValue(String.class);
                address = snapshot.child("address").getValue(String.class);
                address = address + " "+snapshot.child("city").getValue(String.class);

                patient_name_tv.setText(patient_name);
                bloodgp_tv.setText(bloodgp);
                age_tv.setText(age);
                gender_tv.setText(gender);
                maritalStatus_tv.setText(maritalStatus);
                height_tv.setText(height);
                weight_tv.setText(weight);
                phone_tv.setText(phoneNo);
                emergencyPhoneNo_tv.setText(emergencyPhoneNo);
                address_tv.setText(address);

                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(patient_profile_iv);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        basicinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        contactdetail = findViewById(R.id.contactdeatil_btn);
        contactdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientProfileActivity.this, EditContactDetailActivity.class);
                startActivity(intent);
            }
        });

    }
}