package com.example.teledentistry.DoctorModule.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teledentistry.DoctorModule.Adapters.Pat_History_Adapter;
import com.example.teledentistry.DoctorModule.DoctorModel;
import com.example.teledentistry.DoctorModule.Pat_History_Model;
import com.example.teledentistry.PatientModule.Adapters.Specialist_Adapter;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class PatHistoryFragment extends Fragment {
    RecyclerView pat_history_recyclerView;
    Pat_History_Adapter adapter;
    String pat_id;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PatHistoryFragment() {
        // Required empty public constructor
    }

    public PatHistoryFragment(String pat_id) {
        this.pat_id = pat_id;
    }

    public static PatHistoryFragment newInstance(String param1, String param2) {
        PatHistoryFragment fragment = new PatHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pat_history, container, false);
        pat_history_recyclerView = view.findViewById(R.id.pat_history_recyclerView);
        pat_history_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final FirebaseRecyclerOptions<Pat_History_Model> options =
                new FirebaseRecyclerOptions.Builder<Pat_History_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Patients").child(pat_id)
                                .child("Consultation_Done"), Pat_History_Model.class)
                        .build();

        adapter = new Pat_History_Adapter(options, pat_id);
        pat_history_recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.startListening();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }




}