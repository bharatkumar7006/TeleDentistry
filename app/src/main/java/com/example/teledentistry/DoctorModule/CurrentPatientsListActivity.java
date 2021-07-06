package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.example.teledentistry.DoctorModule.Adapters.CurrentPatientDataList_Adapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CurrentPatientsListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView currentPatient_recyclerView;
    Window window;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CurrentPatientDataList_Adapter currentPatientDataList_adapter;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;
    private LinearLayoutManager manager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_patients_list_doc_module);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        final Context context;
        context = getApplicationContext();

        currentPatient_recyclerView = findViewById(R.id.currentPatient_recycleView);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        DateFormat df = new SimpleDateFormat("d, YYYY");
        Calendar calobj = Calendar.getInstance();
        String month_name = new SimpleDateFormat("MMM").format(calobj.getTime());
        String today = month_name +" "+ df.format(calobj.getTime());
        Log.d("today1", today);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        final FirebaseRecyclerOptions<BookedSlots_Model> options =
                new FirebaseRecyclerOptions.Builder<BookedSlots_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Doctors").child(userId).child("booked slots").orderByChild("date").equalTo(today), BookedSlots_Model.class)
                        .build();

        currentPatientDataList_adapter = new CurrentPatientDataList_Adapter(context, options);
        manager = new LinearLayoutManager(this);
        currentPatient_recyclerView.setLayoutManager(manager);
        currentPatient_recyclerView.setAdapter(currentPatientDataList_adapter);
        currentPatientDataList_adapter.notifyDataSetChanged();
        currentPatientDataList_adapter.startListening();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        currentPatient_recyclerView.addItemDecoration(dividerItemDecoration);

    }


    @Override
    protected void onStart() {
        super.onStart();
        currentPatientDataList_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(currentPatientDataList_adapter != null) {
            currentPatientDataList_adapter.stopListening();
        }
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
                Intent i = new Intent(CurrentPatientsListActivity.this, AboutActivity_Doc.class);
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
