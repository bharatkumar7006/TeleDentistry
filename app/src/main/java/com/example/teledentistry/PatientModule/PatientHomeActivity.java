package com.example.teledentistry.PatientModule;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.LoginActivity_Doc_Module;
import com.example.teledentistry.PatientModule.Adapters.SliderAdapter;
import com.example.teledentistry.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView patient_name_tv, email,header_patient_name,header_email;
    CircleImageView patient_profile_iv,online_iv,offline_iv,header_patient_profile_iv;
    int[] img = {R.drawable.d1_doc_module, R.drawable.d2_doc_module, R.drawable.d333_doc_module};
    SliderAdapter slideradapter;
    String patient_name;
    Button Specialist_btn, freeconsultaion_btn, findDoctor_btn, myconsultation_btn, articles_blogs, chatbot_btn, supportteam;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_pat_module);

        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        patient_name_tv = findViewById(R.id.patientName_tv);
        email = findViewById(R.id.email_tv);
        patient_profile_iv = findViewById(R.id.profile_imageView);
        online_iv = findViewById(R.id.online_iv);
        offline_iv = findViewById(R.id.offline_iv);
        navigationView = findViewById(R.id.navigation);

        View headerView = navigationView.getHeaderView(0);
        header_patient_profile_iv = headerView.findViewById(R.id.header_patient_profile_iv);
        header_patient_name = headerView.findViewById(R.id.header_patient_name);
        header_email = headerView.findViewById(R.id.header_email);



        patient_profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientHomeActivity.this, PatientProfileActivity.class);
                startActivity(i);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            Intent intent = new Intent(PatientHomeActivity.this, hearderPatientModule.class);
            startActivity(intent);
        }


        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        SliderView sliderView = findViewById(R.id.imageSlider);
        slideradapter = new SliderAdapter(img);
        sliderView.setSliderAdapter(slideradapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        Specialist_btn = findViewById(R.id.specialist_btn);
        Specialist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, SpecialistActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        });
        freeconsultaion_btn = findViewById(R.id.freeconsultation_btn);
        freeconsultaion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PatientHomeActivity.this, FreeConsultattionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        });
        findDoctor_btn = findViewById(R.id.finddoc_btn);
        findDoctor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PatientHomeActivity.this, FindDoctorActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        });
        myconsultation_btn = findViewById(R.id.myconsultation_btn);
        myconsultation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PatientHomeActivity.this, MyConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        });
        articles_blogs = findViewById(R.id.blogs_and_articles_btn);
        articles_blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PatientHomeActivity.this, WebView_SplashActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        });
        chatbot_btn = findViewById(R.id.chatbot_btn);
        chatbot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PatientHomeActivity.this, ChatBotActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        });
        supportteam = findViewById(R.id.supportteam_btn);
        supportteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialPad(PatientHomeActivity.this, "03056465790");
            }
        });

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity_Pat_Module.class));
        }

        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userId = user.getUid();

        email.setText(user.getEmail());
        header_patient_profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientHomeActivity.this, PatientProfileActivity.class);
                startActivity(i);
            }
        });

        patient_profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientHomeActivity.this, PatientProfileActivity.class);
                startActivity(i);
            }
        });


                reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patient_name = snapshot.child("full_name").getValue(String.class);
                patient_name_tv.setText(patient_name);
                header_patient_name.setText(snapshot.child("full_name").getValue(String.class));
                header_email.setText(user.getEmail());
                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(patient_profile_iv);
                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(header_patient_profile_iv);

                if(snapshot.child("status").getValue().equals("online")){
                    online_iv.setVisibility(View.VISIBLE);
                    offline_iv.setVisibility(View.GONE);
                }
                else{
                    online_iv.setVisibility(View.GONE);
                    offline_iv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void status(String status){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("status",status);
        reference.updateChildren(hashMap);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        status("online");
    }


    public static void openDialPad(Activity activity, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        activity.startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent2 = new Intent(PatientHomeActivity.this, PatientHomeActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_consult_now: {
                Intent intent2 = new Intent(PatientHomeActivity.this, FreeConsultattionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_my_consultation: {
                Intent intent2 = new Intent(PatientHomeActivity.this, MyConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_profile: {
                Intent intent2 = new Intent(PatientHomeActivity.this, PatientProfileActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_logout: {
                openDialog();
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_requested_consultation: {
                Intent intent2 = new Intent(PatientHomeActivity.this, RequestedConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_about: {
                Intent intent2 = new Intent(PatientHomeActivity.this, Aboutactivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_terms_and_conditions: {
                Intent intent2 = new Intent(PatientHomeActivity.this, TermsAndConditionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }
        }
        switch (item.getItemId()) {
            case R.id.nav_setting: {
                Intent intent2 = new Intent(PatientHomeActivity.this, settingActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }


        }


        return true;
    }

    public void openDialog() {
        OpenLogoutClass openLogoutClass = new OpenLogoutClass();
        openLogoutClass.show(getSupportFragmentManager(), "open_logout_class");
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}