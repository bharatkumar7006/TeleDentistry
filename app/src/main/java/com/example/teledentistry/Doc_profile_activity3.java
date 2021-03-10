package com.example.teledentistry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Doc_profile_activity3 extends AppCompatActivity {
    Button finish_btn;
    Spinner year_experience_spinner, month_experience_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile_activity3);

        finish_btn = findViewById(R.id.finish_btn);
        year_experience_spinner = findViewById(R.id.yearExperience_spinner);
        month_experience_spinner = findViewById(R.id.monthExperience_spinner);

        ArrayAdapter yearArrayAdapter = ArrayAdapter.createFromResource(this,R.array.year_experience ,R.layout.color_year_experience_spinner_layout);
        year_experience_spinner.setAdapter(yearArrayAdapter);

        ArrayAdapter monthArrayAdapter = ArrayAdapter.createFromResource(this, R.array.monthExperience, R.layout.color_month_experience_spinner_layout);
        month_experience_spinner.setAdapter(monthArrayAdapter);



        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }
}