package com.example.teledentistry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginTabFragment extends Fragment {
    TextInputEditText email_EditText,pass_EditText;
    TextInputLayout email_textInputLayout, pass_textInputLayout;
    Button doctorLoginBtn;
    TextView or_tv,forgetpass;
    float v=0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        email_EditText = root.findViewById(R.id.doc_email);
        pass_EditText = root.findViewById(R.id.doc_passwrd);
        email_textInputLayout = root.findViewById(R.id.email_textInputLayout);
        pass_textInputLayout = root.findViewById(R.id.passwrd_textInputLayout);
        forgetpass = root.findViewById(R.id.forgotPass_tv);
        doctorLoginBtn = root.findViewById(R.id.doctorLoginBtn);
        or_tv = root.findViewById(R.id.or_tv);

        doctorLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DoctorHomeActivity.class);
                startActivity(i);
            }
        });



        //Animation
        email_EditText.setTranslationX(800);
        pass_EditText.setTranslationX(800);
        email_textInputLayout.setTranslationX(800);
        pass_textInputLayout.setTranslationX(800);
        forgetpass.setTranslationX(800);
        doctorLoginBtn.setTranslationX(800);
        or_tv.setTranslationX(800);

        email_EditText.setAlpha(v);
        pass_EditText.setAlpha(v);
        email_textInputLayout.setAlpha(v);
        pass_textInputLayout.setAlpha(v);
        forgetpass.setAlpha(v);
        doctorLoginBtn.setAlpha(v);
        or_tv.setAlpha(v);

        email_EditText.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass_EditText.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        email_textInputLayout.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass_textInputLayout.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        doctorLoginBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        or_tv.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        return  root;
    }

}
