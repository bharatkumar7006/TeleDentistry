package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.teledentistry.R;
import com.google.android.material.navigation.NavigationView;

public class RequestedConsultationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
Button cancel,next;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_consultation_pat_module);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navigation);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        cancel=findViewById(R.id.cancel_btn);
        next=findViewById(R.id.connected);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RequestedConsultationActivity.this, AfterConnectionActivity.class);
                startActivity(intent);
            }
        });
    }
    public void openDialog(){
        OpenDialogCancelclass openDialogCancleclass=new OpenDialogCancelclass();
        openDialogCancleclass.show(getSupportFragmentManager(),"open_dialog_cancel_class");


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}