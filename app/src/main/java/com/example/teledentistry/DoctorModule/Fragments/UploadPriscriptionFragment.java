package com.example.teledentistry.DoctorModule.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teledentistry.DoctorModule.PrescriptionAdapter;
import com.example.teledentistry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UploadPriscriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UploadPriscriptionFragment extends Fragment {
    Button diagnoses_btn, tabs_btn, add_btn,addTabs_btn,send_btn;
    Dialog tabs_dialog, diagnoses_dialog;
    private LinearLayoutManager manager;
    EditText diagnoses_editText,editText, tabs_editText, dosage_editText, comments_editText;
    List<String> tabs_list;
    RecyclerView tabsList_recyclerView;
    String pat_id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UploadPriscriptionFragment() {
        // Required empty public constructor
    }

    public UploadPriscriptionFragment(String pat_id) {
        this.pat_id = pat_id;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UploadPriscriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UploadPriscriptionFragment newInstance(String param1, String param2) {
        UploadPriscriptionFragment fragment = new UploadPriscriptionFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_priscription_doc_module, container, false);
        tabs_btn = view.findViewById(R.id.tab_btn);
        diagnoses_btn = view.findViewById(R.id.diagnoses_btn);
        editText = view.findViewById(R.id.editText);
        tabsList_recyclerView = view.findViewById(R.id.tabs_rc);
        send_btn = view.findViewById(R.id.send_btn);

        diagnoses_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagnoses_dialog = new Dialog(v.getContext());
                diagnoses_dialog.setContentView(R.layout.diagnoses_dialog_box);
                diagnoses_editText = diagnoses_dialog.findViewById(R.id.diagnoses_editText);
                add_btn = diagnoses_dialog.findViewById(R.id.add_btn);

                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText.setText(diagnoses_editText.getText().toString());
                    }
                });
                
                diagnoses_dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                diagnoses_dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                diagnoses_dialog.show();

            }
        });

        tabs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabs_list = new ArrayList<>();

                tabs_dialog = new Dialog(v.getContext());
                tabs_dialog.setContentView(R.layout.tbs_dialog_box);
                tabs_editText = tabs_dialog.findViewById(R.id.tbs_editText);
                dosage_editText = tabs_dialog.findViewById(R.id.dosage_editText);
                comments_editText = tabs_dialog.findViewById(R.id.comments_editText);
                addTabs_btn = tabs_dialog.findViewById(R.id.addTabs_btn);

                addTabs_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String userId = user.getUid();

                        String data = tabs_editText.getText().toString()+";"+dosage_editText.getText().toString()+
                                ";"+comments_editText.getText().toString();
                        tabs_list.add(data);
                        PrescriptionAdapter prescriptionAdapter = new PrescriptionAdapter(getContext(),tabs_list);
                        tabsList_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        tabsList_recyclerView.setAdapter(prescriptionAdapter);
                        prescriptionAdapter.notifyDataSetChanged();

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors").child(userId)
                                .child("Prescriptions").push();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("tabs",data);
                        hashMap.put("diagnoses",diagnoses_editText.getText().toString());
                        hashMap.put("pat_id",pat_id);
                        reference.setValue(hashMap);

                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Patients").child(pat_id)
                                .child("Prescriptions").push();
                        HashMap<String, Object> hashMap1 = new HashMap<>();
                        hashMap1.put("tabs",data);
                        hashMap1.put("diagnoses", diagnoses_editText.getText().toString());
                        hashMap1.put("doc_id", userId);
                        reference1.setValue(hashMap1);

                        tabs_editText.setText("");
                        dosage_editText.setText("");
                        comments_editText.setText("");


                    }
                });



                tabs_dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                tabs_dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                tabs_dialog.show();
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Prescriptions are sent !!!",Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}