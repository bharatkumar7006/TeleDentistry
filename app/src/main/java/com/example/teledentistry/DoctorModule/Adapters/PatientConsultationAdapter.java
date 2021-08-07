package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.DoctorModule.Fragments.CallFragment;
import com.example.teledentistry.DoctorModule.Fragments.ChatFragment;
import com.example.teledentistry.DoctorModule.Fragments.PatHistoryFragment;
import com.example.teledentistry.DoctorModule.Fragments.RecievedAssessmentFragment;
import com.example.teledentistry.DoctorModule.Fragments.UploadPriscriptionFragment;

public class PatientConsultationAdapter extends FragmentPagerAdapter {

    private Context context;
    int totaltabs;
    String pat_id;

    public PatientConsultationAdapter(@NonNull FragmentManager fm, int behavior, Context context, int totaltabs, String pat_id) {
        super(fm, behavior);
        this.context = context;
        this.totaltabs = totaltabs;
        this.pat_id = pat_id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            PatHistoryFragment patHistoryFragment = new PatHistoryFragment(pat_id);
            return patHistoryFragment;
        } else {
            UploadPriscriptionFragment uploadPriscriptionFragment = new UploadPriscriptionFragment(pat_id);
            return uploadPriscriptionFragment;
        }
    }

    @Override
    public int getCount(){
        return totaltabs;
    }
}
