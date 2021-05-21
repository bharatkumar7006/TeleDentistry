package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.DoctorModule.Fragments.All_Consulted_Patient_Fragment;
import com.example.teledentistry.DoctorModule.Fragments.Consulted_Cancle_Fragment;
import com.example.teledentistry.DoctorModule.Fragments.Consulted_Dropped_Fragment;
import com.example.teledentistry.DoctorModule.Fragments.Consulted_Missed_Fragment;
import com.example.teledentistry.DoctorModule.Fragments.Consulted_Patient_Fragment;
import com.example.teledentistry.DoctorModule.Fragments.Consulted_Rejected_Fragment;

public class PatientConsultedAdapter extends FragmentPagerAdapter {
    private Context context;
    int totaltabs;

    public PatientConsultedAdapter(@NonNull FragmentManager fm, int behavior,Context context, int totaltabs) {
        super(fm, behavior);
        this.context = context;
        this.totaltabs = totaltabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            All_Consulted_Patient_Fragment all_consulted_patient_fragment = new All_Consulted_Patient_Fragment();
            return all_consulted_patient_fragment;
        }
        if(position == 1) {
            Consulted_Patient_Fragment consulted_patient_fragment = new Consulted_Patient_Fragment();
            return consulted_patient_fragment;
        }
        if(position == 2){
            Consulted_Missed_Fragment consulted_missed_fragment = new Consulted_Missed_Fragment();
            return consulted_missed_fragment;
        }
        if(position == 3){
            Consulted_Dropped_Fragment consulted_dropped_fragment = new Consulted_Dropped_Fragment();
            return consulted_dropped_fragment;
        }
        if(position == 4){
            Consulted_Cancle_Fragment consulted_cancle_fragment = new Consulted_Cancle_Fragment();
            return consulted_cancle_fragment;
        }
        else{
         Consulted_Rejected_Fragment consulted_rejected_fragment = new Consulted_Rejected_Fragment();
         return consulted_rejected_fragment;
        }

    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
