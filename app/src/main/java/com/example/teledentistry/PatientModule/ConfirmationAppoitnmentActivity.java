package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;
import com.google.android.material.textfield.TextInputEditText;

public class ConfirmationAppoitnmentActivity extends AppCompatActivity {
    Button pay, cancel;
    ImageButton cross;
    Spinner bookingFor;
    TextInputEditText reasonFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_appoitnment_pat_module);
        pay = findViewById(R.id.booked);
        cancel = findViewById(R.id.cancel);
        bookingFor = findViewById(R.id.booking_for);
        reasonFor = findViewById(R.id.reasonForAppointmentEditText);


        Intent i = getIntent();
        final String date, bookedSlot,fee;
        date = i.getStringExtra("date");
        bookedSlot = i.getStringExtra("bookedSlot");
        fee = i.getStringExtra("fee");
        final String numb = i.getStringExtra("numb");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationAppoitnmentActivity.this, AppointPaymentMethodSpecialistActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("bookedSlot",bookedSlot);
                intent.putExtra("bookingFor",bookingFor.getSelectedItem().toString());
                intent.putExtra("reasonFor",reasonFor.getText().toString());
                intent.putExtra("fee",fee);
                intent.putExtra("numb",numb);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationAppoitnmentActivity.this, SpecialistActivity.class);
                startActivity(intent);
            }
        });
        cross = findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationAppoitnmentActivity.this, AppointmentActivity.class);
                startActivity(intent);
            }
        });


    }
}