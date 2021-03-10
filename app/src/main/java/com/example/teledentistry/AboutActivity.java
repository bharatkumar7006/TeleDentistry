package com.example.teledentistry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView about_tv;
    TextView terms_and_conditions_tv;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        about_tv = findViewById(R.id.about_tv);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        if(Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        // ActionBar and Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        InputStream inputStream = getResources().openRawResource(R.raw.about);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i!=-1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        }catch (Exception e){

        }

        about_tv.setText(byteArrayOutputStream.toString());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent i = new Intent(AboutActivity.this, DoctorHomeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_current_patient: {
                Intent i = new Intent(AboutActivity.this, CurrentPatientsListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_consulted_patient: {
                Intent i = new Intent(AboutActivity.this, ConsultedPatientListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_appoitments: {
                Intent i = new Intent(AboutActivity.this, AllAppointmentsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_account: {
                Intent i = new Intent(AboutActivity.this, Doc_Account_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_profile:{
                Intent i = new Intent(AboutActivity.this, Doc_Main_Profile_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_terms_and_conditions: {
                Intent i = new Intent(AboutActivity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(AboutActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
            default:
                return false;
        }
        return true;
    }
}