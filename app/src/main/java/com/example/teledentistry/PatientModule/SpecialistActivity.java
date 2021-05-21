package com.example.teledentistry.PatientModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.DoctorModel;
import com.example.teledentistry.PatientModule.Adapters.Specialist_Adapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpecialistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private LinearLayoutManager manager;
    private Specialist_Adapter specialist_adapter;
    DatabaseReference reference;
    CheckBox checkBox;

    RecyclerView recyclerView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ArrayList<DoctorModel> doctorModelArrayList, doctorModelArrayList2;
    DoctorModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_pat_module);

        recyclerView =(RecyclerView) findViewById(R.id.recycleView);
        manager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        doctorModelArrayList = new ArrayList<>();

        final FirebaseRecyclerOptions<DoctorModel> options =
                new FirebaseRecyclerOptions.Builder<DoctorModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Doctors"), DoctorModel.class)
                        .build();

        final Context context;
        context = getApplicationContext();
        specialist_adapter=new Specialist_Adapter(options,context);
        recyclerView.setAdapter(specialist_adapter);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String usersId = firebaseAuth.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Patients").child(usersId);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    final FirebaseRecyclerOptions<DoctorModel> options1 =
                            new FirebaseRecyclerOptions.Builder<DoctorModel>()
                                    .setQuery(FirebaseDatabase.getInstance()
                                            .getReference("Doctors").orderByChild("status").equalTo("online"), DoctorModel.class)
                                    .build();
                    specialist_adapter=new Specialist_Adapter(options1,context);

                    recyclerView.setAdapter(specialist_adapter);
                    specialist_adapter.notifyDataSetChanged();
                    specialist_adapter.startListening();

                }
                if(!checkBox.isChecked()){
                    final FirebaseRecyclerOptions<DoctorModel> options =
                            new FirebaseRecyclerOptions.Builder<DoctorModel>()
                                    .setQuery(FirebaseDatabase.getInstance()
                                            .getReference("Doctors"), DoctorModel.class)
                                    .build();
                    specialist_adapter=new Specialist_Adapter(options,context);
                    recyclerView.setAdapter(specialist_adapter);
                    specialist_adapter.notifyDataSetChanged();
                    specialist_adapter.startListening();

                }
            }
        });



//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String s;
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//


    }
//
//private void status(String status){
//    HashMap<String,Object> hashMap = new HashMap<>();
//    hashMap.put("status",status);
//    reference.updateChildren(hashMap);
//}
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        status("online");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        status("offline");
//    }

    @Override
    protected void onStart() {
        super.onStart();
        specialist_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        specialist_adapter.stopListening();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, PatientHomeActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_consult_now:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, FreeConsultattionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_my_consultation:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, MyConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_profile:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, PatientProfileActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_logout:
            {
                openDialog();
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_requested_consultation:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, RequestedConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_about:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, Aboutactivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_terms_and_conditions:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, TermsAndConditionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_setting:
            {
                Intent intent2 =new Intent(SpecialistActivity.this, settingActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }


        }
        return true;
    }
    public void openDialog(){
        OpenLogoutClass openLogoutClass=new OpenLogoutClass();
        openLogoutClass.show(getSupportFragmentManager(),"open_logout_class");


    }
}
