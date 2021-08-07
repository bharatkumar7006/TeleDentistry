package com.example.teledentistry.PatientModule.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.Consulted_Patient_Model;
import com.example.teledentistry.DoctorModule.Adapters.ConsultedPatientListAdapter;
import com.example.teledentistry.PatientModule.Adapters.Consultation_Done_ListAdapter;
import com.example.teledentistry.PatientModule.Consultation_Done_Model;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static org.webrtc.ContextUtils.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Consulted_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Consulted_Fragment extends Fragment {
    RecyclerView consultation_done_Patient_recyclerView;
    Consultation_Done_ListAdapter consultation_done_listAdapter;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;
    private LinearLayoutManager manager;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Consulted_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Consulted_Patient_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Consulted_Fragment newInstance(String param1, String param2) {
        Consulted_Fragment fragment = new Consulted_Fragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_consulted_pat_module, container, false);

        consultation_done_Patient_recyclerView = root.findViewById(R.id.pat_consulted_recyclerView);

        final Context context;
        context = getContext();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();


        final FirebaseRecyclerOptions<Consultation_Done_Model> options =
                new FirebaseRecyclerOptions.Builder<Consultation_Done_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Patients").child(userId).child("Consultation_Done"), Consultation_Done_Model.class)
                        .build();

        consultation_done_listAdapter  = new Consultation_Done_ListAdapter(context, options,userId);
        manager = new LinearLayoutManager(context);
        consultation_done_Patient_recyclerView.setLayoutManager(manager);
        consultation_done_Patient_recyclerView.setAdapter(consultation_done_listAdapter);
        consultation_done_listAdapter.notifyDataSetChanged();
        consultation_done_listAdapter.startListening();


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        consultation_done_listAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(consultation_done_listAdapter != null) {
            consultation_done_listAdapter.stopListening();
        }
    }



}