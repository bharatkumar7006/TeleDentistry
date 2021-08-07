package com.example.teledentistry.DoctorModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.teledentistry.DoctorModule.Adapters.AccountAdapter;
import com.example.teledentistry.PatientModule.Adapters.Specialist_Adapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Doc_Account_Activity extends AppCompatActivity {
    Window window;
    RecyclerView table_recyclerView;
    AccountAdapter accountAdapter;
    Context context;
   LinearLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__account_doc_module);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        table_recyclerView = findViewById(R.id.table_recyclerView);
        manager = new LinearLayoutManager(this);


        Intent i = getIntent();
        String doc_id = i.getStringExtra("doc_id");

        Log.d("iddd", doc_id);

        final FirebaseRecyclerOptions<TransectionModel> options =
                new FirebaseRecyclerOptions.Builder<TransectionModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Transaction").orderByChild(doc_id), TransectionModel.class)
                        .build();

        context = getApplicationContext();
        accountAdapter = new AccountAdapter(options,context);
        table_recyclerView.setAdapter(accountAdapter);
        table_recyclerView.hasFixedSize();
        table_recyclerView.setLayoutManager(manager);
        accountAdapter.notifyDataSetChanged();
        accountAdapter.startListening();







    }
    @Override
    protected void onStart() {
        super.onStart();
        accountAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accountAdapter.stopListening();
    }
}