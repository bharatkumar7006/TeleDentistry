package com.example.teledentistry.DoctorModule.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teledentistry.DoctorModule.DoctorHomeActivity;
import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginTabFragment extends Fragment {
    TextInputLayout email, password;
    Button doctorLoginBtn;
    TextView or_tv, forgetpass;
    ProgressDialog progressDialog;
    float v=0;
    FirebaseAuth firebaseAuth;
    Spinner spinner;

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            getActivity().finish();
            Intent i = new Intent(getContext(), DoctorHomeActivity.class);
            startActivity(i);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment_doc_module,container,false);

        progressDialog = new ProgressDialog(getContext());

        email = root.findViewById(R.id.email_textInputLayout);
        password = root.findViewById(R.id.passwrd_textInputLayout);
        forgetpass = root.findViewById(R.id.forgotPass_tv);
        doctorLoginBtn = root.findViewById(R.id.doctorLoginBtn);
        or_tv = root.findViewById(R.id.or_tv);
        spinner = root.findViewById(R.id.spinner);

//        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
//        itemSelected=sharedPreferences.getString("Doctor","");

        //Animation
        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetpass.setTranslationX(800);
        doctorLoginBtn.setTranslationX(800);
        or_tv.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetpass.setAlpha(v);
        doctorLoginBtn.setAlpha(v);
        or_tv.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        doctorLoginBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        or_tv.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        doctorLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        return  root;
    }


    private void login(){
        final String txt_email = email.getEditText().getText().toString();
        String txt_password = password.getEditText().getText().toString();

        if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) ){
            Toast.makeText(getContext(), "All field are required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(txt_email)){
            email.setError("Field cannot be empty");
        }
       else if(TextUtils.isEmpty(txt_password)){
            password.setError("Field cannot be empty");
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            password.setError(null);
            password.setErrorEnabled(false);
        }



        progressDialog.setMessage("Logging In Please Wait...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(txt_email,txt_password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful()){
                                getActivity().finish();
                                Intent intent = new Intent(getContext().getApplicationContext(), DoctorHomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("email",txt_email);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getContext().getApplicationContext(),"Authentication failed!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
    
}
