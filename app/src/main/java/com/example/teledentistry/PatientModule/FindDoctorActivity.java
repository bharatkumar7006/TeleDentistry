package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class FindDoctorActivity extends AppCompatActivity {
Button searchspecaility_btn;
Spinner searchSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor_pat_module);
        searchspecaility_btn = (Button)findViewById(R.id.searchspec);
        searchSpinner = findViewById(R.id.spinner7);

        searchspecaility_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchSpinner.getSelectedItem().equals("Select Speciality")){
                    Toast.makeText(getApplicationContext(),"Please select the doctor speciality for search",Toast.LENGTH_SHORT).show();
                }
                else{
                Intent intent=new Intent(FindDoctorActivity.this, SearchResultActivity.class);
                intent.putExtra("speciality", searchSpinner.getSelectedItem().toString());
                startActivity(intent);
                }
            }
        });

    }
}