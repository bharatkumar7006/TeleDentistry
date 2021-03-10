package com.example.teledentistry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class PatientConsultationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TabLayout tabLayout2;
    ViewPager viewPager2;
    Window window;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_consultation);
        tabLayout2=findViewById(R.id.tab_layout2);
        viewPager2=findViewById(R.id.viewpager2);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


        final PatientConsultationAdapter patientConsultationAdapter = new PatientConsultationAdapter(getSupportFragmentManager(),0,this,tabLayout2.getTabCount());
        viewPager2.setAdapter(patientConsultationAdapter);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent i = new Intent(getApplicationContext(), DoctorHomeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_current_patient: {
                Intent i = new Intent(getApplicationContext(), CurrentPatientsListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_consulted_patient: {
                Intent i = new Intent(getApplicationContext(), ConsultedPatientListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_appoitments: {
                Intent i = new Intent(getApplicationContext(), AllAppointmentsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_account: {
                Intent i = new Intent(getApplicationContext(), Doc_Account_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_profile:{
                Intent i = new Intent(getApplicationContext(), Doc_Main_Profile_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_terms_and_conditions: {
                Intent i = new Intent(PatientConsultationActivity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(PatientConsultationActivity.this, AboutActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(PatientConsultationActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }

            default:
                return false;
        }
        return true;
    }
}