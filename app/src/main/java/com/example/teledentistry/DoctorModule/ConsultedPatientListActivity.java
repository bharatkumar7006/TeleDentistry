package com.example.teledentistry.DoctorModule;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.Window;

import com.example.teledentistry.Consulted_Patient_Model;
import com.example.teledentistry.DoctorModule.Adapters.ConsultedPatientListAdapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ConsultedPatientListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView consultedPatient_recyclerView;
    Window window;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String consulted_patient[];

    ConsultedPatientListAdapter consultedPatientListAdapter;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;
    private LinearLayoutManager manager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulted_patient_listd_doc_module);


        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        consultedPatient_recyclerView = findViewById(R.id.consultedPatient_recycleView);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);

        context = getApplicationContext();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        final FirebaseRecyclerOptions<Consulted_Patient_Model> options =
                new FirebaseRecyclerOptions.Builder<Consulted_Patient_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Doctors").child(userId).child("Consulted_Patient"), Consulted_Patient_Model.class)
                        .build();

        consultedPatientListAdapter = new ConsultedPatientListAdapter(context, options);
        manager = new LinearLayoutManager(this);
        consultedPatient_recyclerView.setLayoutManager(manager);
        consultedPatient_recyclerView.setAdapter(consultedPatientListAdapter);
        consultedPatientListAdapter.notifyDataSetChanged();
        consultedPatientListAdapter.startListening();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        consultedPatient_recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onStart() {
        super.onStart();
        consultedPatientListAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(consultedPatientListAdapter != null) {
            consultedPatientListAdapter.stopListening();
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
                Intent i = new Intent(ConsultedPatientListActivity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(ConsultedPatientListActivity.this, AboutActivity_Doc.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(ConsultedPatientListActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
            default:
                return false;
        }
        return true;
    }
}