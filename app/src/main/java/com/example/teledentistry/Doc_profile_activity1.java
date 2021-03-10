package com.example.teledentistry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.teledentistry.R;

public class Doc_profile_activity1 extends AppCompatActivity {
    Spinner gender_spinner, maritalstatus_spinner;
    Button next1_btn;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile_activity1);
         gender_spinner = findViewById(R.id.gender_spinner);
         maritalstatus_spinner = findViewById(R.id.maritalstatus_spinner);
         next1_btn = (Button) findViewById(R.id.next1);
         constraintLayout = findViewById(R.id.doc_profile_activity1_layout);

        ArrayAdapter genderarray_Adapter = ArrayAdapter.createFromResource(this,R.array.spinner_gender_item,R.layout.color_gender_spinner_layout);
        genderarray_Adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        gender_spinner.setAdapter(genderarray_Adapter);


        ArrayAdapter maritalstatus_arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_maritalstatus_item,R.layout.color_maritalstatus_spinner_layout);
        maritalstatus_arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        maritalstatus_spinner.setAdapter(maritalstatus_arrayAdapter);

        next1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Doc_profile_activity2.class);
                startActivity(i);

            }
        });


    }
}