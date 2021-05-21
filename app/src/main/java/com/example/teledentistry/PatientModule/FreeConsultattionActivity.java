package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.PatientModule.Adapters.FreeConsultationAdapter;
import com.example.teledentistry.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FreeConsultattionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private LinearLayoutManager manager;
    private List<ModelFreeConsultation> myitems;
    private FreeConsultationAdapter freeConsultationAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_pat_module);


        recyclerView =(RecyclerView) findViewById(R.id.recycleView);
        myitems=new ArrayList<>();
        freeConsultationAdapter=new FreeConsultationAdapter(this, myitems);
        manager= new LinearLayoutManager(this);
        recyclerView.setAdapter(freeConsultationAdapter);
        recyclerView.setLayoutManager(manager);
        myitems.add(new ModelFreeConsultation("Doctor Name","Experience","Status","FREE","Slot Available","Specialitist","Orthodontist", R.id.imageView11, R.id.imageView8, R.id.button9, R.id.button10, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8));
        myitems.add(new ModelFreeConsultation("Doctor Name","Experience","Status","FREE","Slot Available","Specialitist","Orthodontist", R.id.imageView11, R.id.imageView8, R.id.button9, R.id.button10, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8));
        myitems.add(new ModelFreeConsultation("Doctor Name","Experience","Status","FREE","Slot Available","Specialitist","Orthodontist", R.id.imageView11, R.id.imageView8, R.id.button9, R.id.button10, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8));
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
    switch(item.getItemId()){

        case R.id.nav_home:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, PatientHomeActivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
        }}
        switch(item.getItemId()){
        case R.id.nav_consult_now:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, FreeConsultattionActivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
        }}
        switch(item.getItemId()){
        case R.id.nav_my_consultation:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, MyConsultationActivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
        }}
        switch(item.getItemId()){
        case R.id.nav_profile:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, PatientProfileActivity.class);
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
            Intent intent2 =new Intent(FreeConsultattionActivity.this, RequestedConsultationActivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
        }}
        switch(item.getItemId()){
        case R.id.nav_about:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, Aboutactivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
        }}
        switch(item.getItemId()){
        case R.id.nav_terms_and_conditions:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, TermsAndConditionActivity.class);
            startActivity(intent2);
            overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
        }}
        switch(item.getItemId()){
        case R.id.nav_setting:
        {
            Intent intent2 =new Intent(FreeConsultattionActivity.this, settingActivity.class);
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
