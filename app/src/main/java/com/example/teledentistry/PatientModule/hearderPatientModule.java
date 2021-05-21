package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.LoginActivity_Doc_Module;
import com.example.teledentistry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class hearderPatientModule extends Fragment {
  TextView header_patient_name_tv, header_email_tv;
  FirebaseAuth firebaseAuth;
  DatabaseReference reference;
  String patient_name;
  ImageView patient_profile_iv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.patient_nav_header_pat_module, container, false);
     //   header_patient_name_tv = root.findViewById(R.id.header_patient_name);
       // header_email_tv = root.findViewById(R.id.header_email);
        patient_profile_iv = root.findViewById(R.id.header_patient_profile_iv);



        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userId = user.getUid();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);

        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getContext(), LoginActivity_Pat_Module.class));
        }


        header_email_tv.setText(user.getEmail());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patient_name = snapshot.child("full_name").getValue(String.class);
                header_patient_name_tv.setText(patient_name);
                Glide.with(getContext()).load(snapshot.child("imageUrl").getValue()).into(patient_profile_iv);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        return root;
    }


}
