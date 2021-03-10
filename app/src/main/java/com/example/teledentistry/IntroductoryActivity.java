package com.example.teledentistry;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {
ImageView logo,background;
TextView appname;
LottieAnimationView lottieAnimationView;
private static  final int  NUM_PAGES =3;
private ViewPager viewPager;
private ScreenSlidePagerAdapter pagerAdapter;
Animation anim;
Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        background=(ImageView)findViewById(R.id.image1);
        logo=(ImageView)findViewById(R.id.image2);
        appname=(TextView)findViewById(R.id.app);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);

        viewPager =findViewById(R.id.pager);
        pagerAdapter=new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        anim= AnimationUtils.loadAnimation(this, R.anim.my_anim);
        viewPager.startAnimation(anim);

        background.animate().translationY(-2800).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(2500).setDuration(1000).setStartDelay(4000);
        appname.animate().translationY(2500).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2500).setDuration(1000).setStartDelay(4000);


        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{


        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFragment1 tab1=new OnBoardingFragment1();
                    return  tab1;
                case 1:
                    OnBoardingFragment2 tab2=new OnBoardingFragment2();
                    return  tab2;
                case 2:
                    OnBoardingFragment3 tab3=new OnBoardingFragment3();
                    return  tab3;

            }
            return  null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }
}