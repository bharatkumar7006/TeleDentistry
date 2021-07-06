package com.example.teledentistry.PatientModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.teledentistry.DoctorModule.DoctorModel;
import com.example.teledentistry.PatientModule.Adapters.SearcResultAdapter;
import com.example.teledentistry.PatientModule.Adapters.Specialist_Adapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SearchResultActivity extends AppCompatActivity {
    private LinearLayoutManager manager;
    private SearcResultAdapter searcResultAdapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        recyclerView = findViewById(R.id.search_recyclerView);
        manager = new LinearLayoutManager(this);


        Intent intent = getIntent();
        String speciality = intent.getStringExtra("speciality");

        final FirebaseRecyclerOptions<DoctorModel> options =
                new FirebaseRecyclerOptions.Builder<DoctorModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Doctors").orderByChild("speciality").equalTo(speciality), DoctorModel.class)
                        .build();
        final Context context;
        context = getApplicationContext();
        searcResultAdapter = new SearcResultAdapter(options,context);
        recyclerView.setAdapter(searcResultAdapter);
        recyclerView.setLayoutManager(manager);
        searcResultAdapter.notifyDataSetChanged();
        searcResultAdapter.startListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        searcResultAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        searcResultAdapter.stopListening();
    }



}