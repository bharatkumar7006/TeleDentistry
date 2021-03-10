package com.example.teledentistry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import static com.google.android.material.tabs.TabLayout.*;

public class Calender_and_Schedule_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TabLayout tabLayout;
    TabLayout.Tab tab;
    TextView date_tv,eTime_tv,sTime_tv;
    int sTime_hours,sTime_mints,eTime_hours,eTime_mints ;
    RecyclerView layout_list;
    TimePickerDialog timePickerDialog;
    Window window;
    Button addSlot_btn,cancle_btn,set_btn;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_and__schedule_);
        //drawerLayout = findViewById(R.id.drawer_Layout);
        //navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
        date_tv = findViewById(R.id.date_tv);
        sTime_tv = findViewById(R.id.sTime_tv);
        eTime_tv = findViewById(R.id.eTime_tv);
        layout_list = findViewById(R.id.layout_list);
        addSlot_btn = findViewById(R.id.addSlot_btn);
        list = new ArrayList<>();

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        // ActionBar and Navigation
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        //drawerLayout.addDrawerListener(toggle);
        //toggle.syncState();
        //navigationView.bringToFront();
        //navigationView.setNavigationItemSelectedListener(this);

        Intent i = getIntent();
        String dayName = i.getStringExtra("key");
        String date = i.getStringExtra("date");

        if(dayName.equalsIgnoreCase("MONDAY")){
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(0);
            tab.select();
        }else if(dayName.equalsIgnoreCase("TUESDAY")){
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(1);
            tab.select();
        }
        else if(dayName.equalsIgnoreCase("WEDNESDAY")){
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(2);
            tab.select();
        }
        else if(dayName.equalsIgnoreCase("THURSDAY")){
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(3);
            tab.select();
        }
        else if(dayName.equalsIgnoreCase("FRIDAY")){
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(4);
            tab.select();
        }
        else if(dayName.equalsIgnoreCase("SATURDAY")){
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(5);
            tab.select();
        }
        else{
            @SuppressLint("CutPasteId") TabLayout dayOfWeek_tabLayout = findViewById(R.id.dayOfWeek_tabLayout);
            tab = (TabLayout.Tab) dayOfWeek_tabLayout.getTabAt(6);
            tab.select();
        }
        date_tv.setText(date);

        sTime_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(Calender_and_Schedule_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sTime_hours = hourOfDay;
                        sTime_mints = minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,sTime_hours,sTime_mints);
                        sTime_tv.setText(DateFormat.format("hh:mm aa",calendar));
                    }
                }, 12, 0,false);
                timePickerDialog.updateTime(sTime_hours,sTime_mints);
                timePickerDialog.show();
            }
        }); // End of setOnClickListener

        eTime_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                 timePickerDialog = new TimePickerDialog(Calender_and_Schedule_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        eTime_hours = hourOfDay;
                        eTime_mints = minute;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,eTime_hours,eTime_mints);
                        eTime_tv.setText(DateFormat.format("hh:mm aa",calendar));
                    }
                }, 12, 0,false);
                timePickerDialog.updateTime(eTime_hours,eTime_mints);
                timePickerDialog.show();

            }
        }); // End of setOnClickListener

        addSlot_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.time_slots, null, false);
                MaterialRadioButton materialRadioButton1 = view.findViewById(R.id.radiobtn_1);
                if(sTime_tv.getText().equals("start time") && eTime_tv.getText().equals("end time") ){
                    Toast.makeText(Calender_and_Schedule_Activity.this, "Please Select Start Time and End Time", Toast.LENGTH_LONG).show();
                }
                else if(sTime_tv.getText().equals("start time")){
                    Toast.makeText(Calender_and_Schedule_Activity.this, "Please Select Start Time", Toast.LENGTH_LONG).show();
                }
                else if(eTime_tv.getText().equals("end time")){
                    Toast.makeText(Calender_and_Schedule_Activity.this, "Please Select End Time", Toast.LENGTH_LONG).show();
                }
                else {

                    list.add(sTime_tv.getText().toString() + "-" + eTime_tv.getText().toString());

                }

                Slot_RecyclerView_Adapter slot_recyclerViewAdapter = new Slot_RecyclerView_Adapter(Calender_and_Schedule_Activity.this, view,list);
                layout_list.setHasFixedSize(true);
                layout_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                layout_list.setAdapter(slot_recyclerViewAdapter);
                sTime_tv.setText("start time");
                eTime_tv.setText("end time");


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
                Intent i = new Intent(Calender_and_Schedule_Activity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(Calender_and_Schedule_Activity.this, AboutActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(Calender_and_Schedule_Activity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
            default:
                return false;
        }
        return true;
    }
}
