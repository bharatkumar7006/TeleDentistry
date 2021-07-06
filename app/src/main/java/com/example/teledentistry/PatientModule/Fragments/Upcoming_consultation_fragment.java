package com.example.teledentistry.PatientModule.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.BookedSlots_Model;
import com.example.teledentistry.PatientModule.Adapters.Upcoming_Patients_List_Adapter;
import com.example.teledentistry.PatientModule.SpecialistActivity;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Upcoming_consultation_fragment extends Fragment {
    CircleImageView new_appointment;
    RecyclerView upcoming_recyclerView;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;
    private LinearLayoutManager manager;
    public static Button btn;

    Upcoming_Patients_List_Adapter upcoming_patients_list_adapter;

    public Upcoming_consultation_fragment() {
        // Required empty public constructor
    }


    public static Upcoming_consultation_fragment newInstance(String param1, String param2) {
        Upcoming_consultation_fragment fragment = new Upcoming_consultation_fragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_upcomig_consultation_fragment_pat_module, container, false);
        new_appointment = root.findViewById(R.id.new_appointment);
        upcoming_recyclerView = root.findViewById(R.id.upcoming_recyclerView);

        final Context context;
        context = getContext();


        DateFormat df = new SimpleDateFormat("d, YYYY");
        Calendar calobj = Calendar.getInstance();
        String month_name = new SimpleDateFormat("MMM").format(calobj.getTime());
        String today = month_name +" "+ df.format(calobj.getTime());
        Log.d("todayyy", today);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        final FirebaseRecyclerOptions<BookedSlots_Model> options =
                new FirebaseRecyclerOptions.Builder<BookedSlots_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Patients").child(userId).child("booked slots").orderByChild("date").equalTo(today), BookedSlots_Model.class)
                        .build();

        upcoming_patients_list_adapter = new Upcoming_Patients_List_Adapter(options,context);
        manager = new LinearLayoutManager(context);
        upcoming_recyclerView.setLayoutManager(manager);
        upcoming_recyclerView.setAdapter(upcoming_patients_list_adapter);
        upcoming_patients_list_adapter.notifyDataSetChanged();
        upcoming_patients_list_adapter.startListening();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        upcoming_recyclerView.addItemDecoration(dividerItemDecoration);

        new_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SpecialistActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        upcoming_patients_list_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
            upcoming_patients_list_adapter.stopListening();
    }

}