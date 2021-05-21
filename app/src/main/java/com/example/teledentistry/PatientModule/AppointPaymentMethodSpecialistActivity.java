package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class AppointPaymentMethodSpecialistActivity extends AppCompatActivity {
    Button credit,jazzaccount,easypaisa,proceed;
    boolean clicked=false,clicked2=false,clicked3=false;
    String date, bookedSlot,bookingFor,reasonFor,fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_payment_method_specialist_pat_module);
        credit=findViewById(R.id.creditCard_btn);
        jazzaccount=findViewById(R.id.jazzCash_btn);
        easypaisa=findViewById(R.id.easyPaisa_btn);
        proceed=findViewById(R.id.proceed_btn);

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed.setVisibility(View.VISIBLE);
                clicked=true;

            }
        });
        jazzaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed.setVisibility(View.VISIBLE);
                clicked2=true;

            }
        });
        easypaisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed.setVisibility(View.VISIBLE);
                clicked3=true;
            }
        });

        Intent i = getIntent();
        date = i.getStringExtra("date");
        bookedSlot  = i.getStringExtra("bookedSlot");
        bookingFor = i.getStringExtra("bookingFor");
        reasonFor = i.getStringExtra("reasonFor");
        fee = i.getStringExtra("fee");
        final String numb = i.getStringExtra("numb");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked){
                    Intent intent=new Intent(AppointPaymentMethodSpecialistActivity.this, AppointmentCreditPaySpecialistActivity.class);
                    intent.putExtra("date",date);
                    intent.putExtra("bookedSlot",bookedSlot);
                    intent.putExtra("bookingFor",bookingFor);
                    intent.putExtra("reasonFor",reasonFor);
                    intent.putExtra("fee",fee);
                    intent.putExtra("numb",numb);

                    startActivity(intent);
                }
                else if(clicked2){
                    Intent intent=new Intent(AppointPaymentMethodSpecialistActivity.this, AppointmentJazzPaySpecialistActivity.class);
                    startActivity(intent);

                }
                else if(clicked3){
                    Intent intent=new Intent(AppointPaymentMethodSpecialistActivity.this, AppointmentEasyPaisaPaySpecialistActivity.class);
                    startActivity(intent);


                }
            }
        });
    }
}