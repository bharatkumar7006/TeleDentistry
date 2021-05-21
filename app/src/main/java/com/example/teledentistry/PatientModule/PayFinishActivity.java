package com.example.teledentistry.PatientModule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class PayFinishActivity extends AppCompatActivity {
Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_pay_pat_module);
        finish=findViewById(R.id.finish_btn);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog(){
OpenDialogclass openDialogclass=new OpenDialogclass();
openDialogclass.show(getSupportFragmentManager(),"open_dialog_class");


    }
}