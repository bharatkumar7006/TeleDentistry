package com.example.teledentistry.PatientModule.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teledentistry.DoctorModule.Doc_profile_activity1;
import com.example.teledentistry.DoctorModule.Doc_profile_activity2;
import com.example.teledentistry.PatientModule.BasicProfileActivity;
import com.example.teledentistry.PatientModule.PatientHomeActivity;
import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupTabFragment extends Fragment {
    Button signup;
    EditText pname, pemail, ppass, pcpass;
    FirebaseAuth fAuth;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients").push();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment_pat_module, container, false);
        signup = (Button) root.findViewById(R.id.button);
        pname = (EditText) root.findViewById(R.id.name);
        pemail = (EditText) root.findViewById(R.id.email);
        ppass = (EditText) root.findViewById(R.id.editTextPassword);
        pcpass = (EditText) root.findViewById(R.id.editTextPassword2);

        fAuth = FirebaseAuth.getInstance();

   /*     if (fAuth.getCurrentUser() != null) {

            Intent intent = new Intent(getActivity(), PatientHomeActivity.class);
            startActivity(intent);
        }
*/
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = pname.getText().toString().trim();
                final String email = pemail.getText().toString().trim();
                final String password = ppass.getText().toString().trim();
                final String cpassword = pcpass.getText().toString().trim();

                if (TextUtils.isEmpty(fullname)) {
                    pemail.setError("Full Name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    pemail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ppass.setError("Password is Required.");
                    return;
                }
                if (password.length() < 6) {
                    ppass.setError("Password must be greater then 6 or more characters.");
                    return;
                }
                if (TextUtils.isEmpty(cpassword)) {
                    pcpass.setError("Confirm a password.");
                    return;
                }
                Intent i = new Intent(getContext(), BasicProfileActivity.class);
                i.putExtra("full_name",fullname);
                i.putExtra("email",email);
                i.putExtra("password",password);
                i.putExtra("confirm_password",cpassword);

                startActivity(i);


/*
                //register user in firebase
                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = fAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);
                            HashMap<String, Object> hashMap = new HashMap<>();

                            hashMap.put("full_name", fullname);
                            hashMap.put("email", email);
                            hashMap.put("password", password);
                            hashMap.put("confirm_password", cpassword);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getActivity(), BasicProfileActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "You cann't register with this email or password" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Toast.makeText(getActivity(), "User successfully register", Toast.LENGTH_SHORT).show();
                */

            }
        });
        return root;
    }

}
