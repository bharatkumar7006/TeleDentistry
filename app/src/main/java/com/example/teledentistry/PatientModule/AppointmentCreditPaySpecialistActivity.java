package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class AppointmentCreditPaySpecialistActivity extends AppCompatActivity {
    Button pay;
    String date, bookedSlot,bookingFor,reasonFor,fee;
    TextInputEditText cardNumber_tf,mobilenum,cvv,expirdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_credit_pay_specialist_pat_module);
        pay=findViewById(R.id.paybtn);
        cardNumber_tf = findViewById(R.id.cardNumber_tf);
        mobilenum = findViewById(R.id.mobilenum);
        cvv = findViewById(R.id.cvv);
        expirdate = findViewById(R.id.expirdate);


        Intent i = getIntent();
        date = i.getStringExtra("date");
        bookedSlot  = i.getStringExtra("bookedSlot");
        bookingFor = i.getStringExtra("bookingFor");
        reasonFor = i.getStringExtra("reasonFor");
        fee = i.getStringExtra("fee");
        final String numb = i.getStringExtra("numb");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppointmentCreditPaySpecialistActivity.this, AppointmentpayFinishSpecialistActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("bookedSlot",bookedSlot);
                intent.putExtra("bookingFor",bookingFor);
                intent.putExtra("reasonFor",reasonFor);
                intent.putExtra("fee",fee);
                intent.putExtra("card_number",cardNumber_tf.getText().toString());
                intent.putExtra("mobile_number",mobilenum.getText().toString());
                intent.putExtra("cvv",cvv.getText().toString());
                intent.putExtra("expire_date",expirdate.getText().toString());
                intent.putExtra("numb",numb);

                startActivity(intent);
            }
        });
    }
}