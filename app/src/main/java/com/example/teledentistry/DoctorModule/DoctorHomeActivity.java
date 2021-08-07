package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.PatientModule.OpenLogoutClass;
import com.example.teledentistry.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CountDownTimer countDownTimer;
    long timeInMilliSec = 900000;
    boolean time;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button currentPatient_btn, consultedPatient_btn, calender_btn, appoitments_btn, account_btn, blogs_and_articles_btn;
    private CircleImageView doctor_profile_iv;
    TextView timeLeft_tv, doc_Email, doc_Name, nav_header_name_tv, nav_header_email_tv;
    String name, imageUrl;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    String userId;
    DoctorModel model;
    CircleImageView online_iv, offline_iv, nav_header_iv;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_doc_module);

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
        doc_Name = findViewById(R.id.doc_Name);
        doc_Email = findViewById(R.id.doc_Email);
        online_iv = findViewById(R.id.online_iv);
        offline_iv = findViewById(R.id.offline_iv);

        //  online_iv.setVisibility(View.VISIBLE);
        //offline_iv.setVisibility(View.VISIBLE);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

    //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity_Doc_Module.class));
        }


        // ActionBar and Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        nav_header_iv = header.findViewById(R.id.nav_header_iv);
        nav_header_name_tv = header.findViewById(R.id.nav_header_name_tv);
        nav_header_email_tv = header.findViewById(R.id.nav_header_email_tv);


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
                materialDatePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });


        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                try {
                    SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("EEEE");

                    String dayName = simpleDateFormatDay.format(selection);

                    Intent i = new Intent(getApplicationContext(), Calender_and_Schedule_Activity.class);
                    i.putExtra("key", dayName);
                    i.putExtra("date", materialDatePicker.getHeaderText());
                    startActivity(i);

                } catch (Exception e) {
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
                i.putExtra("doc_id", userId);
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


        doc_Email.setText(user.getEmail());

        Intent i = getIntent();
        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(userId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("full_name").getValue(String.class);
                doc_Name.setText(name);
                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(doctor_profile_iv);
                Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue()).into(nav_header_iv);
                nav_header_name_tv.setText(name);
                nav_header_email_tv.setText(user.getEmail());

                if (snapshot.child("status").getValue().equals("online")) {
                    online_iv.setVisibility(View.VISIBLE);
                    offline_iv.setVisibility(View.GONE);
                } else {
                    online_iv.setVisibility(View.GONE);
                    offline_iv.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //FirebaseTime - currentTime == 5 then send Notification to that particular patient.
//
//        DateFormat df = new SimpleDateFormat("dd, YYYY");
//        Calendar calobj = Calendar.getInstance();
//        String month_name = new SimpleDateFormat("MMM").format(calobj.getTime());
//        String today1 = month_name +" "+ df.format(calobj.getTime());
//        Log.d("today1", today1);
//
//        Query reference1;
//        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(userId); //.orderByChild("booked slots").equalTo(today1);
//        final List<String> time_list = new ArrayList<>();
//
//        reference.child("booked slots").addListenerForSingleValueEvent(new ValueEventListener() {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            String s = "";
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
//                    Log.d("hashMappp", String.valueOf(hashMap.get("time")));
//                }
//                String time = (String) hashMap.get("time");
//                String complt_time[] = time.split("-");
//                String consult_time = complt_time[0];
//                String actual_time[];
//                if(consult_time.contains("AM")){
//                    actual_time = consult_time.split("AM");
//                }
//                else{
//                    actual_time = consult_time.split("PM");
//                }
//                String ac_time = actual_time[0];
//                Log.d("conslttt", String.valueOf(ac_time));
//
//                DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'");
//                String current_time = simpleDateFormat.format(new Date());
//
//
//                Date date1= null;
//                Date date2 = null;
//                try {
//                    date1 = simpleDateFormat.parse(ac_time);
//                    date2 = simpleDateFormat.parse(current_time);
//                    long difference = date2.getTime() - date1.getTime();
//                    Log.d("ddd", String.valueOf(date1));
//                    Log.d("ddd", String.valueOf(date2));
//                    System.out.println(date1);
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//
//////                    long differenceInHours
//////                            = (differenceInMilliSeconds / (60 * 60 * 1000))
//////                            % 24;
//////
//////                    // Calculating the difference in Minutes
//////                    long differenceInMinutes
//////                            = (differenceInMilliSeconds / (60 * 1000)) % 60;
////
////                    Log.d("difff",differenceInHours+" "+differenceInMinutes);
//
//                Log.d("currenttt", String.valueOf(current_time));
//
//
////                    time_list.add(snapshot.getValue(String.class));
//
////
////                Log.d("time_list", String.valueOf(time_list));
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//






    }


    private void status(String status) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);
        reference.updateChildren(hashMap);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        status("online");
    }

    private void updateTimer() {
        int minutes = (int) timeInMilliSec / 60000;
        int seconds = (int) timeInMilliSec % 60000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 15) timeLeftText += "0";
        timeLeftText += seconds;

        timeLeft_tv.setText("Time left for next consultation " + timeLeftText);

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
            case R.id.nav_profile: {
                Intent i = new Intent(DoctorHomeActivity.this, Doc_Main_Profile_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(DoctorHomeActivity.this, AboutActivity_Doc.class);
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
            case R.id.nav_logout: {
                openDialog();
                break;
            }

            default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void openDialog() {
        OpenLogoutClass_Doc_Module openLogoutClass_doc_module = new OpenLogoutClass_Doc_Module();
        openLogoutClass_doc_module.show(getSupportFragmentManager(), "open_logout_class");
    }


}