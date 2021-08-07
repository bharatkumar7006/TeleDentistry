package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class AppointPaymentMethodSpecialistActivity extends AppCompatActivity {
    Button jazzaccount,easypaisa;
    boolean clicked=false,clicked2=false,clicked3=false;
    String date, bookedSlot,bookingFor,reasonFor,fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_payment_method_specialist_pat_module);
        jazzaccount=findViewById(R.id.jazzCash_btn);
        easypaisa=findViewById(R.id.easyPaisa_btn);

        Intent i = getIntent();
        date = i.getStringExtra("date");
        bookedSlot  = i.getStringExtra("bookedSlot");
        bookingFor = i.getStringExtra("bookingFor");
        reasonFor = i.getStringExtra("reasonFor");
        fee = i.getStringExtra("fee");
        final String numb = i.getStringExtra("numb");

//        jazzaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                proceed.setVisibility(View.VISIBLE);
////                clicked=true;
//
//                Intent intent=new Intent(AppointPaymentMethodSpecialistActivity.this, AppointmentJazzPaySpecialistActivity.class);
//                intent.putExtra("date",date);
//                intent.putExtra("bookedSlot",bookedSlot);
//                intent.putExtra("bookingFor",bookingFor);
//                intent.putExtra("reasonFor",reasonFor);
//                intent.putExtra("fee",fee);
//                intent.putExtra("numb",numb);
//                intent.putExtra("clicked", clicked);
//
//                startActivity(intent);
//
//
//
//            }
//        });
        jazzaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                proceed.setVisibility(View.VISIBLE);
//                clicked2=true;

                Intent intent=new Intent(AppointPaymentMethodSpecialistActivity.this, AppointmentJazzPayFinishSpecialistActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("bookedSlot",bookedSlot);
                intent.putExtra("bookingFor",bookingFor);
                intent.putExtra("reasonFor",reasonFor);
                intent.putExtra("fee",fee);
                intent.putExtra("doc_numb",numb);
                intent.putExtra("clicked2", Boolean.toString(clicked2));

                startActivity(intent);

            }
        });
        easypaisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                proceed.setVisibility(View.VISIBLE);
//                clicked3=true;

                Intent intent=new Intent(AppointPaymentMethodSpecialistActivity.this, AppointmentEasyPaisaPaySpecialistActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("bookedSlot",bookedSlot);
                intent.putExtra("bookingFor",bookingFor);
                intent.putExtra("reasonFor",reasonFor);
                intent.putExtra("fee",fee);
                intent.putExtra("numb",numb);
                intent.putExtra("clicked3", clicked3);

                startActivity(intent);

            }
        });

    }
}