package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import com.example.teledentistry.DoctorModule.Doc_profile_activity3;
import com.example.teledentistry.DoctorModule.LoginActivity_Doc_Module;
import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

public class ContactInformationActivity extends AppCompatActivity {
    Locale[] locales = Locale.getAvailableLocales();
    Spinner country_spinner;
    EditText address, city, phone_no,emergency_no;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    Button next;
    String txt_country,txt_address,txt_city,txt_phone_no,txt_emergency_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information_pat_module);
        country_spinner=findViewById(R.id.country);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        phone_no = findViewById(R.id.Phonenum);
        emergency_no = findViewById(R.id.Enum);
        next = findViewById(R.id.next);

        ArrayAdapter countryArrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_country_list,R.layout.color_country_spinner_layout_doc_module);
        country_spinner.setAdapter(countryArrayAdapter);

        firebaseAuth = FirebaseAuth.getInstance();

       /* ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }

        } */

       /* Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        } */
/*
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(ContactInformationActivity.this,android.R.layout.simple_spinner_item, countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the your spinner
        country_spinner.setAdapter(countryAdapter);
        */

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase();
            }
        });

    }

    private void uploadToFirebase() {
        txt_address = address.getText().toString();
        txt_city = city.getText().toString();
        txt_country = country_spinner.getSelectedItem().toString();
        txt_phone_no = phone_no.getText().toString();
        txt_emergency_no = emergency_no.getText().toString();

        if (TextUtils.isEmpty(txt_address) || TextUtils.isEmpty(txt_city) || TextUtils.isEmpty(txt_country) ||
                TextUtils.isEmpty(txt_phone_no) || TextUtils.isEmpty(txt_emergency_no)) {
            Toast.makeText(ContactInformationActivity.this, "All field are required", Toast.LENGTH_SHORT).show();
        } else {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String userId = firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Patients");

        reference.child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String, Object> postHashMap = new HashMap<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            postHashMap.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        }

                        postHashMap.put("address", txt_address);
                        postHashMap.put("city", txt_city);
                        postHashMap.put("country", txt_country);
                        postHashMap.put("phone_no", txt_phone_no);
                        postHashMap.put("emergency_no", txt_emergency_no);

                        reference.child(userId).updateChildren(postHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent1 = new Intent(ContactInformationActivity.this, LoginActivity_Pat_Module.class);

                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent1);
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    }
}