package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Doc_Main_Profile_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Window window;
    ImageView profile_edit_iv, contact_edit_tv, designation_edit_tv, setting_tv;
    CircleImageView profile_iv;
    TextView doc_name_tv,doc_email_tv,doc_email_tv2,contact_tv,gender_tv,designation_tv;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__main__profile_doc_module);

        profile_edit_iv = findViewById(R.id.profile_edit_iv);
        contact_edit_tv = findViewById(R.id.contact_edit_iv);
        designation_edit_tv = findViewById(R.id.designation_edit_tv);
        drawerLayout = findViewById(R.id.rl);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        setting_tv = findViewById(R.id.setting_iv);
        doc_name_tv = findViewById(R.id.doc_name_tv);
        doc_email_tv = findViewById(R.id.doc_email_tv);
        profile_iv = findViewById(R.id.profile_image);
        doc_email_tv2 = findViewById(R.id.email_tv2);
        contact_tv = findViewById(R.id.number_tv);
        gender_tv = findViewById(R.id.gender_tv);
        designation_tv = findViewById(R.id.designation_actual_tv);

        if (Build.VERSION.SDK_INT >= 21) {
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        // ActionBar and Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        profile_edit_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, Doc_profile_activity1.class);
                startActivity(i);
            }
        });

        contact_edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, Doc_profile_activity2.class);
                startActivity(i);
            }
        });

        designation_edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, Doc_profile_activity3.class);
                startActivity(i);
            }
        });

        setting_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, SettingActivity.class);
                startActivity(i);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(userId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doc_name_tv.setText(snapshot.child("full_name").getValue(String.class));
                doc_email_tv.setText(snapshot.child("email").getValue(String.class));
                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(profile_iv);
                doc_email_tv2.setText(user.getEmail());
                contact_tv.setText(snapshot.child("phone_no").getValue(String.class));
                gender_tv.setText(snapshot.child("gender").getValue(String.class));
                designation_tv.setText(snapshot.child("speciality").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                navigationView.setCheckedItem(item.getItemId());
                Intent i = new Intent(Doc_Main_Profile_Activity.this, DoctorHomeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_current_patient: {
                navigationView.setCheckedItem(item.getItemId());
                Intent i = new Intent(Doc_Main_Profile_Activity.this, CurrentPatientsListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_consulted_patient: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, ConsultedPatientListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_appoitments: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, AllAppointmentsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_account: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, Doc_Account_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_profile: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, Doc_Main_Profile_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, AboutActivity_Doc.class);
                startActivity(i);
                break;
            }
            case R.id.nav_terms_and_conditions: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(Doc_Main_Profile_Activity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
            default:
                return false;
        }
        return true;
    }
}