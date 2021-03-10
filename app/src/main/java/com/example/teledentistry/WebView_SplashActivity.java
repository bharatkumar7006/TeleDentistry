package com.example.teledentistry;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class WebView_SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_webview);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();


        Thread splashScreen = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(getApplicationContext(),Blogs_and_Articles_Activity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        splashScreen.start();


    }
}