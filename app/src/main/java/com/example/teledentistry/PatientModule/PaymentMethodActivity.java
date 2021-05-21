package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.R;

public class PaymentMethodActivity extends AppCompatActivity {
Button credit,jazzaccount,easypaisa,proceed;
    boolean clicked=false,clicked2=false,clicked3=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_pat_module);
        credit=findViewById(R.id.button17);
        jazzaccount=findViewById(R.id.button18);
        easypaisa=findViewById(R.id.button19);
        proceed=findViewById(R.id.button22);
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
proceed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if(clicked){
        Intent intent=new Intent(PaymentMethodActivity.this, CreditPayActivity.class);
        startActivity(intent);}
else if(clicked2){
Intent intent=new Intent(PaymentMethodActivity.this, PayJazzAcountActivity.class);
startActivity(intent);

}
else if(clicked3){
    Intent intent=new Intent(PaymentMethodActivity.this, EasyPaisaPayActivity.class);
    startActivity(intent);


}
    }
});
    }
}