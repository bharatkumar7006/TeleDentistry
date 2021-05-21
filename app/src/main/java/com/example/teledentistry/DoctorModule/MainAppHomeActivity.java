package com.example.teledentistry.DoctorModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.teledentistry.DoctorModule.Fragments.DoctorFragment;
import com.example.teledentistry.DoctorModule.Fragments.EmptyFragment;
import com.example.teledentistry.DoctorModule.Fragments.PatientFragment;
import com.example.teledentistry.R;

public class MainAppHomeActivity extends AppCompatActivity{
    Spinner spinner;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    int currentVideoPosition;
    Animation topAnim, bottomAnim;
    TextView teleDentistryMain_tv;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_home);


        spinner = findViewById(R.id.spinner);

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_home_item,R.layout.color_spinner_home_layout_doc_module);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout_doc_module);
        spinner.setAdapter(arrayAdapter);

        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        //spinner.setAdapter(arrayAdapter);
        teleDentistryMain_tv = (TextView) findViewById(R.id.teleDentistryMain_tv);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animator);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animator);
        teleDentistryMain_tv.setAnimation(topAnim);
        spinner.setAnimation(bottomAnim);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getSelectedItem().toString().equalsIgnoreCase("I am a Patient")){
                    loadPatientFragment(new PatientFragment());

                }
                else if(parent.getSelectedItem().toString().equalsIgnoreCase("I am a Doctor")){
                    loadDoctorFragment(new DoctorFragment());
                    saveSharedPreferences(parent.getSelectedItem().toString());
                }
                else if(parent.getSelectedItem().toString().equalsIgnoreCase("I am a")){
                    loadMainFragment(new EmptyFragment());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void saveSharedPreferences(String itemSelected){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Doctor",itemSelected);
        editor.apply();
    }


/*
    @Override
    protected void onPause() {
        super.onPause();
        currentVideoPosition = mediaPlayer.getCurrentPosition();
        videoView.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
*/
    private void loadMainFragment(EmptyFragment emptyFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment,emptyFragment);
        fragmentTransaction.commit();
    }

    private void loadDoctorFragment(DoctorFragment doctorFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment,doctorFragment);
        fragmentTransaction.commit();
    }

    private void loadPatientFragment(PatientFragment patientFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment,patientFragment);
        fragmentTransaction.commit();
    }

}