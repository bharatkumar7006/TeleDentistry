package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.teledentistry.PatientModule.Adapters.AfterConnectionAdapter;
import com.example.teledentistry.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class AfterConnectionActivity extends AppCompatActivity {
    Window window;
    ImageButton call;
    TabLayout tablayout;
    ViewPager viewPager;
    TextView runningtime;
    boolean timerstart=false;
    Timer timer;
    TimerTask timerTask;
    Double time=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_connection_pat_module);
        tablayout = findViewById(R.id.tab_layout3);
        viewPager = findViewById(R.id.viewpager3);
        final AfterConnectionAdapter afterConnectionAdapter = new AfterConnectionAdapter(getSupportFragmentManager(),0,this,tablayout.getTabCount());
        viewPager.setAdapter(afterConnectionAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        call= findViewById(R.id.call_icon);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AfterConnectionActivity.this, CallAcceptedActicity.class);
                startActivity(intent);
            }
        });
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        runningtime=findViewById(R.id.runningtime);
        timer=new Timer();
        start();

    }
public void start(){
        if(timerstart==false){
            timerstart=true;
        startTimer();}

}
    public  void startTimer(){
        timerTask = new TimerTask() {
            @Override

            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        runningtime.setText(getTimerText());
                    }
                });


            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);

    }
    private String getTimerText(){

        int rounded=(int)Math.round(time);
        int second=((rounded%86400)%3600)%60;
        int minutes=((rounded%86400)%3600)/60;
        int hours=((rounded%86400)/3600);
        return  formatTime(second,minutes,hours);
    }

    private String formatTime(int second, int minutes, int hours) {
        return  String.format("%02d",hours)+":"+String.format("%02d",minutes)+":"+String.format("%02d",second);
    }

}