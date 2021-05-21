package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.DoctorModule.Fragments.CallFragment;
import com.example.teledentistry.DoctorModule.Fragments.ChatFragment;
import com.example.teledentistry.DoctorModule.Fragments.RecievedAssessmentFragment;
import com.example.teledentistry.DoctorModule.Fragments.UploadPriscriptionFragment;

public class PatientConsultationAdapter extends FragmentPagerAdapter {

    private Context context;
    int totaltabs;

    public PatientConsultationAdapter(@NonNull FragmentManager fm, int behavior, Context context, int totaltabs) {
        super(fm, behavior);
        this.context = context;
        this.totaltabs = totaltabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            RecievedAssessmentFragment recievedAssessmentFragment = new RecievedAssessmentFragment();
            return recievedAssessmentFragment;
        }
        if (position == 1) {
            ChatFragment chatFragment = new ChatFragment();
            return chatFragment;
        }
        if (position == 2) {
            UploadPriscriptionFragment uploadPriscriptionFragment = new UploadPriscriptionFragment();
            return uploadPriscriptionFragment;
        } else {
            CallFragment callFragment = new CallFragment();
            return callFragment;
        }
    }

    @Override
    public int getCount(){
        return totaltabs;
    }
}
