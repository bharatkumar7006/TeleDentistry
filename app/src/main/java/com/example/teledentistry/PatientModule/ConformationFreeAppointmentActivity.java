package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class ConformationFreeAppointmentActivity extends AppCompatActivity {
    Button pay,cancel;
    ImageButton cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conformation_free_appointment_pat_module);
        pay=findViewById(R.id.booked);
        cancel=findViewById(R.id.cancel);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConformationFreeAppointmentActivity.this, MyConsultationActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConformationFreeAppointmentActivity.this, FreeConsultattionActivity.class);
                startActivity(intent);
            }
        });
        cross=findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConformationFreeAppointmentActivity.this, FreeAppointmentActivty.class);
                startActivity(intent);
            }
        });
    }
}