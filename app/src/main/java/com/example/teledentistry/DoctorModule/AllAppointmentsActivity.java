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
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.example.teledentistry.DoctorModule.Adapters.AllAppointments_Adapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAppointmentsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Window window;
    RecyclerView recyclerView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Doctors");
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    String pat_id;
    List<Object> datalist;
    String data_of_list;
    String dataArray[];
    int count = 0;

    List<String> adapterList;

    AllAppointments_Adapter allAppointments_adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments_doc_module);
        recyclerView = findViewById(R.id.allAppointments_recyclerView);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        //   reference.keepSynced(true);

        if (Build.VERSION.SDK_INT >= 21) {
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

//        adapterList = new ArrayList<>();

        final Context context;
        context = getApplicationContext();

        // ActionBar and Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        final FirebaseRecyclerOptions<BookedSlots_Model> options =
                new FirebaseRecyclerOptions.Builder<BookedSlots_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Doctors").child(userId).child("booked slots"), BookedSlots_Model.class)
                        .build();

        allAppointments_adapter = new AllAppointments_Adapter(context,options);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(allAppointments_adapter);
        allAppointments_adapter.notifyDataSetChanged();
        allAppointments_adapter.startListening();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(AllAppointmentsActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onStart() {
        super.onStart();
        allAppointments_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(allAppointments_adapter != null) {
            allAppointments_adapter.stopListening();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                navigationView.setCheckedItem(item.getItemId());
                Intent i = new Intent(AllAppointmentsActivity.this, DoctorHomeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_current_patient: {
                navigationView.setCheckedItem(item.getItemId());
                Intent i = new Intent(AllAppointmentsActivity.this, CurrentPatientsListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_consulted_patient: {
                Intent i = new Intent(AllAppointmentsActivity.this, ConsultedPatientListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_appoitments: {
                Intent i = new Intent(AllAppointmentsActivity.this, AllAppointmentsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_account: {
                Intent i = new Intent(AllAppointmentsActivity.this, Doc_Account_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_profile: {
                Intent i = new Intent(AllAppointmentsActivity.this, Doc_Main_Profile_Activity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_about: {
                Intent i = new Intent(AllAppointmentsActivity.this, AboutActivity_Doc.class);
                startActivity(i);
                break;
            }
            case R.id.nav_terms_and_conditions: {
                Intent i = new Intent(AllAppointmentsActivity.this, TermsAndConditionActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_setting: {
                Intent i = new Intent(AllAppointmentsActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            }
            default:
                return false;
        }
        return true;
    }


}