package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.Adapters.PatientConsultationAdapter;
import com.example.teledentistry.R;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;


public class PatientConsultationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        JitsiMeetActivityInterface {
    TabLayout tabLayout2;
    ViewPager viewPager2;
    Window window;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView pat_name_tv, pat_runningTime_tv;
    CircleImageView pat_img;
    CountDownTimer countDownTimer;
    long timeInMilliSec = 900000;
    Dialog dialog;
    EditText codeBox,  meeting_codeBox;
    Button jointBtn, sendCode_btn, call_btn;
    URL serverUrl;
    String phone_no, token;
    Button send_btn;
    String pat_id, date, time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_consultation_doc_module);
        tabLayout2 = findViewById(R.id.tab_layout2);
        viewPager2 = findViewById(R.id.viewpager2);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        pat_name_tv = findViewById(R.id.pat_name_tv);
        pat_runningTime_tv = findViewById(R.id.time_running_tv);
        pat_img = findViewById(R.id.pat_img);
        call_btn = findViewById(R.id.call_btn);


        if (Build.VERSION.SDK_INT >= 21) {
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


        Intent i = getIntent();
        String pat_name = i.getStringExtra("pat_name");
        String imageUrl = i.getStringExtra("imageUrl");
         pat_id = i.getStringExtra("pat_id");
         date = i.getStringExtra("date");
         time = i.getStringExtra("time");

        pat_name_tv.setText(pat_name);
        Glide.with(PatientConsultationActivity.this).load(imageUrl).into(pat_img);

        try {
            serverUrl = new URL("https://meet.jit.si");

            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions
                    .Builder()
                    .setServerURL(serverUrl)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        send_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new Dialog(v.getContext());
//                dialog.setContentView(R.layout.meeting_code_dialog_box);
//                meeting_codeBox = dialog.findViewById(R.id.meeting_codeBox);
//                sendCode_btn = dialog.findViewById(R.id.sendCode_btn);
//
//         //       getToken(pat_id);
//
//                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
//                dialog.show();
//
//            }
//
//
//        });




//        send_btn.setOnClickListener(v -> {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients").child(id);
//            reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    phone_no = snapshot.child("phone_no").getValue(String.class);
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//        });
//

        call_btn.setOnClickListener(v -> {
            dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.meeting_dialog_box_doc);
            codeBox = dialog.findViewById(R.id.codeBox);
            jointBtn = dialog.findViewById(R.id.joinBtn);

            jointBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
//                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
//                            .setRoom(codeBox.getText().toString())
//                            .setWelcomePageEnabled(false)
//                            .build();
//                    JitsiMeetActivity.launch(PatientConsultationActivity.this, options);

                  Intent intent = new Intent(PatientConsultationActivity.this, MeetingActivity.class);
                  intent.putExtra("code",codeBox.getText().toString());
                  intent.putExtra("pat_id", pat_id);
                  intent.putExtra("date", date);
                  intent.putExtra("time", time);
                  intent.putExtra("imageUrl", imageUrl);
                  intent.putExtra("pat_name", pat_name);
                    startActivity(intent);
                }
            });

            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            dialog.show();

        });

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


        final PatientConsultationAdapter patientConsultationAdapter = new PatientConsultationAdapter(getSupportFragmentManager(), 0, this, tabLayout2.getTabCount(), pat_id);
        viewPager2.setAdapter(patientConsultationAdapter);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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



//    private void sendMsg(String phoneNo) {
//
//            int code = 1234;
//            SmsManager sms = SmsManager.getDefault();
//            sms.sendTextMessage(phoneNo, null, "Your consultation time has been started please use this code" + code + " and join the meeting", null, null);
//            Toast.makeText(getApplicationContext(), "Message Sent successfully!",
//                    Toast.LENGTH_LONG).show();
//
//    }
//

    private void updateTimer() {
        int minutes = (int) timeInMilliSec / 60000;
        int seconds = (int) timeInMilliSec % 60000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 15) timeLeftText += "0";
        timeLeftText += seconds;

        pat_runningTime_tv.setText("Time Running of Patient " + timeLeftText);

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
            case R.id.nav_profile: {
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
                Intent i = new Intent(PatientConsultationActivity.this, AboutActivity_Doc.class);
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



    @Override
    public void requestPermissions(String[] strings, int i, PermissionListener permissionListener) {

    }

}