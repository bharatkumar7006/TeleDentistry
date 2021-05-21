package com.example.teledentistry.DoctorModule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teledentistry.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Doc_profile_activity2 extends AppCompatActivity {

    Spinner country_spinner, state_spinner,account_type_spinner;
    Button next2_btn;
    TextInputLayout  doc_Address,doc_City,doc_AccountNo;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile_activity2_doc_module);
        country_spinner = findViewById(R.id.country_spinner);
        state_spinner = findViewById(R.id.state_spinner);
        account_type_spinner = findViewById(R.id.account_type_spinner);
        doc_Address = findViewById(R.id.address_textInputLayout);
        doc_City = findViewById(R.id.city_textInputLayout);
        doc_AccountNo = findViewById(R.id.accountNo_textInputLayout);

        progressDialog = new ProgressDialog(this);


        ArrayAdapter countryArrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_country_list,R.layout.color_country_spinner_layout_doc_module);
        country_spinner.setAdapter(countryArrayAdapter);

        ArrayAdapter stateArrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_state_list,R.layout.color_state_spinner_layout_doc_module);
        state_spinner.setAdapter(stateArrayAdapter);

        ArrayAdapter accountTypeArrayAdapter = ArrayAdapter.createFromResource(this, R.array.doc_account_type, R.layout.color_account_type_spinner_layout_doc_module);
        account_type_spinner.setAdapter(accountTypeArrayAdapter);

        next2_btn = (Button) findViewById(R.id.next2);

        next2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  register_doc_profile2();
                Intent i = getIntent();

                String txt_address = doc_Address.getEditText().getText().toString();
                String txt_city = doc_City.getEditText().getText().toString();
                String txt_accountNo = doc_AccountNo.getEditText().getText().toString();
                String txt_country = country_spinner.getSelectedItem().toString();
                String txt_state = state_spinner.getSelectedItem().toString();
                String txt_accountType = account_type_spinner.getSelectedItem().toString();


                if(TextUtils.isEmpty(txt_address) || TextUtils.isEmpty(txt_city) || TextUtils.isEmpty(txt_accountNo) ||
                        TextUtils.isEmpty(txt_country) || TextUtils.isEmpty(txt_state) || TextUtils.isEmpty(txt_accountType)
                ){
                    Toast.makeText(Doc_profile_activity2.this, "All field are required",Toast.LENGTH_SHORT).show();
                }
                else {
  //                  progressDialog.setMessage("Processing Please Wait...");
//                    progressDialog.show();

                    Intent i2 = new Intent(Doc_profile_activity2.this, Doc_profile_activity3.class);

                    i2.putExtra("address", txt_address);
                    i2.putExtra("city", txt_city);
                    i2.putExtra("accountno", txt_accountNo);
                    i2.putExtra("accountType", txt_accountType);
                    i2.putExtra("country", txt_country);
                    i2.putExtra("state", txt_state);

                    startActivity(i2);
                    finish();
                }

            }
        });

    }


}