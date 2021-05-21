package com.example.teledentistry.PatientModule.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teledentistry.DoctorModule.DoctorHomeActivity;
import com.example.teledentistry.PatientModule.PatientHomeActivity;
import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {
    EditText plemail, plpass;
    TextView forgetpass;
    Button login;
    float v = 0;
    FirebaseAuth fAuth;
    @Override
    public void onStart() {
        super.onStart();

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            getActivity().finish();
            Intent i = new Intent(getContext(), PatientHomeActivity.class);
            startActivity(i);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment_pat_module, container, false);

        plemail = root.findViewById(R.id.email);
        plpass = root.findViewById(R.id.editTextPassword);
        forgetpass = root.findViewById(R.id.textView3);


        login = root.findViewById(R.id.button);

        plemail.setTranslationX(800);
        plpass.setTranslationX(800);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);

        plemail.setAlpha(v);
        plpass.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);

        plemail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        plpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPatient();
            }
        });

        return root;
    }


    private void loginPatient(){
        String email = plemail.getText().toString().trim();
        String password = plpass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            plemail.setError("Email is Required.");
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            plpass.setError("Password is Required.");
            return;
        }
        else if (password.length() < 6) {
            plpass.setError("Password must be greater then 6 or more characters.");
            return;
        }
        else{
            plemail.setError(null);
            plpass.setError(null);
        }

        //authenticate the user
        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getActivity().finish();
                            Toast.makeText(getActivity(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), PatientHomeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(), "Authentication failed!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
