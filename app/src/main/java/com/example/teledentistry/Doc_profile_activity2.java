package com.example.teledentistry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Space;
import android.widget.Spinner;

public class Doc_profile_activity2 extends AppCompatActivity {

    Spinner country_spinner, state_spinner,account_type_spinner;
    Button next2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile_activity2);
        country_spinner = findViewById(R.id.country_spinner);
        state_spinner = findViewById(R.id.state_spinner);
        account_type_spinner = findViewById(R.id.account_type_spinner);

        ArrayAdapter countryArrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_country_list,R.layout.color_country_spinner_layout);
        country_spinner.setAdapter(countryArrayAdapter);

        ArrayAdapter stateArrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_state_list,R.layout.color_state_spinner_layout);
        state_spinner.setAdapter(stateArrayAdapter);

        ArrayAdapter accountTypeArrayAdapter = ArrayAdapter.createFromResource(this, R.array.doc_account_type, R.layout.color_account_type_spinner_layout);
        account_type_spinner.setAdapter(accountTypeArrayAdapter);

        next2_btn = (Button) findViewById(R.id.next2);
        next2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Doc_profile_activity3.class);
                startActivity(i);
            }
        });



    }


}