package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class AppointmentEasyPaisaPaySpecialistActivity extends AppCompatActivity {
    Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_easy_paisa_pay_specialist_pat_module);
        pay=findViewById(R.id.paybtn);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppointmentEasyPaisaPaySpecialistActivity.this, MyConsultationActivity.class);
                startActivity(intent);
            }
        });
    }
}