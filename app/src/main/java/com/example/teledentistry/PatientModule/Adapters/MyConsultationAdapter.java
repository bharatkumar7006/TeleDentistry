package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.PatientModule.Fragments.All_Consultation_Fragment;
import com.example.teledentistry.PatientModule.Fragments.Consultation_Cancled_Fragment;
import com.example.teledentistry.PatientModule.Fragments.Consultation_Dropped_Fragment;
import com.example.teledentistry.PatientModule.Fragments.Consultation_Missed_Fragment;
import com.example.teledentistry.PatientModule.Fragments.Consultation_Rejected_Fragment;
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
        if (position == 0) {
            All_Consultation_Fragment all_consultation_fragment  = new All_Consultation_Fragment();
            return all_consultation_fragment;
        }
        if(position == 1) {
            Upcoming_consultation_fragment upcoming_consultation_fragment = new Upcoming_consultation_fragment();
            return upcoming_consultation_fragment;
        }
        if(position == 2){
            Consulted_Fragment consulted_fragment = new Consulted_Fragment();
            return consulted_fragment;
        }
        if(position == 3){
            Consultation_Missed_Fragment consultation_missed_fragment = new Consultation_Missed_Fragment();
            return consultation_missed_fragment;
        }
        if(position == 4){
            Consultation_Dropped_Fragment consultation_dropped_fragment = new Consultation_Dropped_Fragment();
            return consultation_dropped_fragment;
        }
        if(position==5){

            Consultation_Cancled_Fragment consultation_cancled_fragment = new Consultation_Cancled_Fragment();
            return consultation_cancled_fragment;

        }
        else{
         Consultation_Rejected_Fragment consultation_rejected_fragment = new Consultation_Rejected_Fragment();
         return consultation_rejected_fragment;
        }

    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
