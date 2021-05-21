package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.PatientModule.Fragments.AssessmentFragment;
import com.example.teledentistry.PatientModule.Fragments.ChatFragment;
import com.example.teledentistry.PatientModule.Fragments.MedicalHistoryFragment;
import com.example.teledentistry.PatientModule.Fragments.PastConsultationFragment;
import com.example.teledentistry.PatientModule.Fragments.PrescriptionFragment;

public class AfterConnectionAdapter extends FragmentPagerAdapter {
    private Context context;
    int totaltabs;

    public AfterConnectionAdapter(@NonNull FragmentManager fm, int behavior, Context context, int totaltabs) {
        super(fm, behavior);
        this.context = context;
        this.totaltabs = totaltabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            ChatFragment chatFragment= new ChatFragment();
            return chatFragment;
        }
        if(position == 1) {
            PrescriptionFragment prescriptionFragment = new PrescriptionFragment();
            return prescriptionFragment;
        }
        if(position == 2){
            PastConsultationFragment pastConsultationFragment = new PastConsultationFragment();
            return pastConsultationFragment;
        }
        if(position == 3){
           MedicalHistoryFragment medicalHistoryFragment = new MedicalHistoryFragment();
            return medicalHistoryFragment;
        }
        else{
        AssessmentFragment assesementFragment = new AssessmentFragment();
            return assesementFragment;
        }


    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
