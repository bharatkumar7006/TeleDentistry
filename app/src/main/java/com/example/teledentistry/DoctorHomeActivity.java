package com.example.teledentistry;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.TimeZone;

import static java.time.DayOfWeek.*;

public class DoctorHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CountDownTimer countDownTimer;
    long timeInMilliSec = 900000;
    boolean time;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button currentPatient_btn,consultedPatient_btn,calender_btn,appoitments_btn,account_btn, blogs_and_articles_btn;
    Calendar calendar;
    ImageView doctor_profile_iv;
    TextView timeLeft_tv;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        currentPatient_btn = findViewById(R.id.currentPatient_btn);
        consultedPatient_btn = findViewById(R.id.consultedPatient_btn);
        calender_btn = findViewById(R.id.calender_btn);
        appoitments_btn = findViewById(R.id.appoitments_btn);
        account_btn = findViewById(R.id.account_btn);
        blogs_and_articles_btn = findViewById(R.id.blogs_and_articles_btn);
        doctor_profile_iv = findViewById(R.id.doctor_profile_iv);
        timeLeft_tv = findViewById(R.id.timeLeft_tv);

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

        countDownTimer = new CountDownTimer(timeInMilliSec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMilliSec = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        doctor_profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorHomeActivity.this, Doc_Main_Profile_Activity.class);
                startActivity(i);
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
        builder.setCalendarConstraints(constraintsBuilder.build());



        final MaterialDatePicker materialDatePicker = builder.build();

        currentPatient_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CurrentPatientsListActivity.class);
                startActivity(i);
            }
        });

        consultedPatient_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ConsultedPatientListActivity.class);
                startActivity(i);
            }
        });

        calender_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                    String dayName = simpleDateFormat.format(selection);
                    Intent i = new Intent(getApplicationContext(), Calender_and_Schedule_Activity.class);
                    i.putExtra("key",dayName);
                    i.putExtra("date", materialDatePicker.getHeaderText());
                    startActivity(i);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        appoitments_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorHomeActivity.this, AllAppointmentsActivity.class);
                startActivity(i);
            }
        });

        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorHomeActivity.this, Doc_Account_Activity.class);
                startActivity(i);
            }
        });

        blogs_and_articles_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorHomeActivity.this, WebView_SplashActivity.class);
                startActivity(i);
            }
        });

    }

    private void updateTimer() {
        int minutes = (int) timeInMilliSec / 60000;
        int seconds = (int) timeInMilliSec % 60000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText+=":" ;
        if(seconds < 15) timeLeftText+="0";
        timeLeftText += seconds;

        timeLeft_tv.setText("Time left for next consultation "+timeLeftText);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home: {
                navigationView.setCheckedItem(item.getItemId());
                Intent i = new Intent(DoctorHomeActivity.this, DoctorHomeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_current_patient: {
                navigationView.setCheckedItem(item.getItemId());
                Intent i = new Intent(DoctorHomeActivity.this, CurrentPatientsListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_consulted_patient: {
                Intent i = new Intent(DoctorHomeActivity.this, ConsultedPatientListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_appoitments: {
                Intent i = new Intent(DoctorHomeActivity.this, AllAppointmentsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_account: {
                Intent i = new Intent(DoctorHomeActivity.this, Doc_Account_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_profile:{
                Intent i = new Intent(DoctorHomeActivity.this, Doc_Main_Profile_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(DoctorHomeActivity.this, AboutActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_terms_and_conditions: {
                Intent i = new Intent(DoctorHomeActivity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(DoctorHomeActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
                default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

}