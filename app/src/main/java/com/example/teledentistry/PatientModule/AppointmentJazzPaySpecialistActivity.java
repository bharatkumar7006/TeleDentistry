package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;
import com.google.android.material.textfield.TextInputEditText;

public class AppointmentJazzPaySpecialistActivity extends AppCompatActivity {
    Button pay;
    String date, bookedSlot,bookingFor,reasonFor,fee;
    TextInputEditText mobilenum,cnic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_jazz_pay_specialist_pat_module);
        pay=findViewById(R.id.paybtn);
        mobilenum = findViewById(R.id.mobilenum);
        cnic = findViewById(R.id.cnic);

        Intent i = getIntent();
        date = i.getStringExtra("date");
        bookedSlot  = i.getStringExtra("bookedSlot");
        bookingFor = i.getStringExtra("bookingFor");
        reasonFor = i.getStringExtra("reasonFor");
        fee = i.getStringExtra("fee");
        final String numb = i.getStringExtra("doc_numb");
        String clicked = i.getStringExtra("clicked");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppointmentJazzPaySpecialistActivity.this, AppointmentJazzPayFinishSpecialistActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("bookedSlot",bookedSlot);
                intent.putExtra("bookingFor",bookingFor);
                intent.putExtra("reasonFor",reasonFor);
                intent.putExtra("fee",fee);
                intent.putExtra("mobile_number",mobilenum.getText().toString());
                intent.putExtra("cnic",cnic.getText().toString());
                intent.putExtra("doc_numb",numb);
                intent.putExtra("clicked",clicked);

                startActivity(intent);
            }
        });
    }
}