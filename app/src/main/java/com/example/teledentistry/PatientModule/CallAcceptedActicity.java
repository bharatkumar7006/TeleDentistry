package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class CallAcceptedActicity extends AppCompatActivity {
ImageButton endcall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_accepted_acticity_pat_module);
        endcall=findViewById(R.id.end_icon);
        endcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallAcceptedActicity.this, AfterConnectionActivity.class);
                startActivity(intent);
            }
        });

    }
}