package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.teledentistry.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsultMeActivity extends AppCompatActivity {
    Button pay_btn;
    TextView consltation_fee_tv, pay_tv, profile_name, status_tv;
    CircleImageView profile_iv;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_me_pat_module);
        pay_btn = findViewById(R.id.pay);
        consltation_fee_tv = findViewById(R.id.consltation_fee_tv);
        pay_tv = findViewById(R.id.pay_tv_value);
        profile_name = findViewById(R.id.profile_tv);
        status_tv = findViewById(R.id.status_tv);
        profile_iv = findViewById(R.id.profile_iv);

        Intent i = getIntent();
        String name,fee,status;
        name = i.getStringExtra("name");
        fee = i.getStringExtra("fee");
        status = i.getStringExtra("status");
        uri = Uri.parse( i.getStringExtra("uri") );

        profile_name.setText(name);
        status_tv.setText(status);
        consltation_fee_tv.setText(fee);
        pay_tv.setText(fee);
        Glide.with(getApplicationContext()).load(uri).into(profile_iv);


        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultMeActivity.this, PaymentMethodActivity.class);
                startActivity(intent);
            }
        });



    }
}