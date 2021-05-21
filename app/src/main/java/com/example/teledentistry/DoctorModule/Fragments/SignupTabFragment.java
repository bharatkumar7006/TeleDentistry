package com.example.teledentistry.DoctorModule.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teledentistry.DoctorModule.Doc_profile_activity1;
import com.example.teledentistry.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SignupTabFragment extends Fragment {
    private TextInputLayout docFullNameEditText,doc_SignUpPassword,doc_SignUpPhoneNumber,doc_SignUpEmail,doc_SignUpPractitionerLicense;
    Button signup;
    FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.signup_tab_fragment_doc_module,container,false);
        signup=(Button)root.findViewById(R.id.doc_signup_btn) ;
        docFullNameEditText = (TextInputLayout)root.findViewById(R.id.docFullNameEditText);
        doc_SignUpPhoneNumber = (TextInputLayout)root.findViewById(R.id.docSignUpPhoneEditText);
        doc_SignUpPassword = (TextInputLayout)root.findViewById(R.id.docSignUpPassword);
        doc_SignUpEmail = (TextInputLayout)root.findViewById(R.id.doc_SignUpEmailAddress);
        doc_SignUpPractitionerLicense = (TextInputLayout)root.findViewById(R.id.doc_SignUpPractitionerLicense);

        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String txt_FullName = docFullNameEditText.getEditText().getText().toString();
                final String txt_SignUpPhoneNo = doc_SignUpPhoneNumber.getEditText().getText().toString();
                final String txt_SignUpEmail = doc_SignUpEmail.getEditText().getText().toString();
                final String txt_SignUpPassword = doc_SignUpPassword.getEditText().getText().toString();
                final String txt_doc_SignUpPractitionerLicense = doc_SignUpPractitionerLicense.getEditText().getText().toString();

                if(TextUtils.isEmpty(txt_FullName) || TextUtils.isEmpty(txt_SignUpPhoneNo) || TextUtils.isEmpty(txt_SignUpEmail)
                        || TextUtils.isEmpty(txt_SignUpPassword) || TextUtils.isEmpty(txt_doc_SignUpPractitionerLicense)
                ){
                    Toast.makeText(getContext(), "All field are required",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(txt_SignUpPassword.length() < 6){
                    Toast.makeText(getContext(), "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Intent i = new Intent(getContext(), Doc_profile_activity1.class);
                    i.putExtra("fullname", txt_FullName);
                    i.putExtra("phoneno", txt_SignUpPhoneNo);
                    i.putExtra("email", txt_SignUpEmail);
                    i.putExtra("password", txt_SignUpPassword);
                    i.putExtra("practitionerLicense", txt_doc_SignUpPractitionerLicense);
                    startActivity(i);
                }
            }
        });


        return  root;
    }


}
