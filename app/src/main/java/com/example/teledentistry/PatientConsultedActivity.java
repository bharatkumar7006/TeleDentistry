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

public class PatientConsultedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Window window;
    TabLayout tablayout3;
    ViewPager viewPager3;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_consulted);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        tablayout3 = findViewById(R.id.tab_layout3);
        viewPager3 = findViewById(R.id.viewpager3);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        final PatientConsultedAdapter patientConsultedAdapter = new PatientConsultedAdapter(getSupportFragmentManager(),0,this,tablayout3.getTabCount());
        viewPager3.setAdapter(patientConsultedAdapter);
        viewPager3.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout3));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        tablayout3.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager3.setCurrentItem(tab.getPosition());
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
            case R.id.nav_setting: {
                Intent i = new Intent(PatientConsultedActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
            default:
                return false;
        }
        return true;
    }
}