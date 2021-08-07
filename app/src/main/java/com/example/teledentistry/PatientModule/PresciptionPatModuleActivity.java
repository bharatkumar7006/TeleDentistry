package com.example.teledentistry.PatientModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.teledentistry.DoctorModule.DoctorModel;
import com.example.teledentistry.PatientModule.Adapters.PrescriptionPatModuleAdapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PresciptionPatModuleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PrescriptionPatModuleAdapter prescriptionPatModuleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presciption_pat_module);
        recyclerView = findViewById(R.id.prescription_received_rv);


        Intent i = getIntent();
        String pat_id = i.getStringExtra("pat_id");
        String doc_id = i.getStringExtra("doc_id");


        final FirebaseRecyclerOptions<PrescriptionPatModule_Model> options =
                new FirebaseRecyclerOptions.Builder<PrescriptionPatModule_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Patients").child(pat_id).child("Prescriptions")
                                .orderByChild(doc_id), PrescriptionPatModule_Model.class)
                        .build();

        prescriptionPatModuleAdapter = new PrescriptionPatModuleAdapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(prescriptionPatModuleAdapter);
        prescriptionPatModuleAdapter.notifyDataSetChanged();
        prescriptionPatModuleAdapter.startListening();




    }

    @Override
    protected void onStart() {
        super.onStart();
        prescriptionPatModuleAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        prescriptionPatModuleAdapter.stopListening();
    }



}