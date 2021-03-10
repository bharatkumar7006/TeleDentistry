package com.example.teledentistry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.navigation.NavigationView;

public class CurrentPatientsListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView currentPatient_recyclerView;
    Window window;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String date[], name[], time[] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_patients_list);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        currentPatient_recyclerView = findViewById(R.id.currentPatient_recycleView);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        date = getResources().getStringArray(R.array.date);
        name = getResources().getStringArray(R.array.name);
        time = getResources().getStringArray(R.array.time);

        CurrentPatientDataList_Adapter currentPatientDataList_adapter = new CurrentPatientDataList_Adapter(
                this, date,name,time);

        currentPatient_recyclerView.setHasFixedSize(true);
        currentPatient_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        currentPatient_recyclerView.setAdapter(currentPatientDataList_adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        currentPatient_recyclerView.addItemDecoration(dividerItemDecoration);

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
                Intent i = new Intent(CurrentPatientsListActivity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(CurrentPatientsListActivity.this, AboutActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(CurrentPatientsListActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }


            default:
                return false;
        }
        return true;
    }
}
