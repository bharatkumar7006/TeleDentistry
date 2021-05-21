package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.teledentistry.PatientModule.Fragments.LoginTabFragment;
import com.example.teledentistry.PatientModule.Fragments.SignupTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totaltabs;

    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totaltabs) {
        super(fm);
        this.context=context;
        this.totaltabs=totaltabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment = new SignupTabFragment();
                return signupTabFragment;
            default:
        return null;

    }
    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}