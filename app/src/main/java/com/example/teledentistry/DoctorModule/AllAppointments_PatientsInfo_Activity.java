package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllAppointments_PatientsInfo_Activity extends AppCompatActivity {
    Window window;
    CircleImageView pat_img;
    TextView pat_name_tv, pat_email_tv, pat_age_tv, pat_gender_tv, pat_disease_tv, pat_address_tv, pat_country_tv, pat_state_tv;

    String pat_id;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments__patients_info_doc_module);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        pat_img = findViewById(R.id.pat_img);
        pat_name_tv = findViewById(R.id.pat_name_tv);
        pat_email_tv = findViewById(R.id.pat_email_tv);
        pat_age_tv = findViewById(R.id.pat_age_tv);
        pat_gender_tv = findViewById(R.id.pat_gender_tv);
        pat_disease_tv = findViewById(R.id.pat_disease_tv);
        pat_address_tv = findViewById(R.id.pat_address_tv);
        pat_country_tv = findViewById(R.id.pat_country_tv);
        pat_state_tv = findViewById(R.id.pat_state_tv);

        Intent i = getIntent();
        pat_id = i.getStringExtra("pat_id");

        reference = FirebaseDatabase.getInstance().getReference("Patients").child(pat_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(pat_img);
                pat_name_tv.setText(snapshot.child("full_name").getValue(String.class));
                pat_email_tv.setText(snapshot.child("email").getValue(String.class));
                pat_age_tv.setText(snapshot.child("age").getValue(String.class));
                pat_gender_tv.setText(snapshot.child("gender").getValue(String.class));
               // pat_disease_tv.setText(snapshot.child());
                pat_address_tv.setText(snapshot.child("address").getValue(String.class));
                pat_country_tv.setText(snapshot.child("country").getValue(String.class));
                pat_state_tv.setText(snapshot.child("state").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}