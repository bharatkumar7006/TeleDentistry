package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class FindDoctorActivity extends AppCompatActivity {
Button searchspecaility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor_pat_module);
        searchspecaility=(Button)findViewById(R.id.searchspec);
        searchspecaility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this, SpecialistActivity.class);
                startActivity(intent);
            }
        });

    }
}