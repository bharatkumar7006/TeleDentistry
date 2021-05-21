package com.example.teledentistry.DoctorModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teledentistry.DoctorModule.Fragments.ChangePasswordFragment;
import com.example.teledentistry.R;

public class SettingActivity extends AppCompatActivity {
    TextView reset_password_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_doc_module);

        reset_password_tv = findViewById(R.id.reset_password_tv);
        reset_password_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadChangePasswordFragment(new ChangePasswordFragment());
            }
        });


    }
    private void loadChangePasswordFragment(ChangePasswordFragment changePasswordFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.reset_password_main_frameLayout,changePasswordFragment);
        fragmentTransaction.commit();
    }


}