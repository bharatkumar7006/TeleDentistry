package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.teledentistry.PatientModule.Adapters.MyConsultationAdapter;
import com.example.teledentistry.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MyConsultationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TabLayout tablayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_consultation_pat_module);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        tablayout = findViewById(R.id.tab_layout3);
        viewPager = findViewById(R.id.viewpager3);
        final MyConsultationAdapter myConsultationAdapter = new MyConsultationAdapter(getSupportFragmentManager(),0,this,tablayout.getTabCount());
        viewPager.setAdapter(myConsultationAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.nav_home:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, PatientHomeActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_consult_now:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, FreeConsultattionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_my_consultation:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, MyConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_profile:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, PatientProfileActivity.class);
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
                Intent intent2 =new Intent(MyConsultationActivity.this, RequestedConsultationActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_about:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, Aboutactivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_terms_and_conditions:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, TermsAndConditionActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_out);
            }}
        switch(item.getItemId()){
            case R.id.nav_setting:
            {
                Intent intent2 =new Intent(MyConsultationActivity.this, settingActivity.class);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}