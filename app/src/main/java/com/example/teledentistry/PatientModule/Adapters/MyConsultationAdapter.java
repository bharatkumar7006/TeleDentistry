package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.PatientModule.Fragments.Consulted_Fragment;
import com.example.teledentistry.PatientModule.Fragments.Upcoming_consultation_fragment;

public class MyConsultationAdapter extends FragmentPagerAdapter {
    private Context context;
    int totaltabs;

    public MyConsultationAdapter(@NonNull FragmentManager fm, int behavior, Context context, int totaltabs) {
        super(fm, behavior);
        this.context = context;
        this.totaltabs = totaltabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            Upcoming_consultation_fragment upcoming_consultation_fragment = new Upcoming_consultation_fragment();
            return upcoming_consultation_fragment;
        }
        else{
            Consulted_Fragment consulted_fragment = new Consulted_Fragment();
            return consulted_fragment;
        }

    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
