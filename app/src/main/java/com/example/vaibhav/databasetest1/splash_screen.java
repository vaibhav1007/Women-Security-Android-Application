package com.example.vaibhav.databasetest1;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.transition.Slide;
import android.view.Window;
import android.view.WindowManager;

public class splash_screen extends AppCompatActivity {
int x;
    private final int SPLASH_DISPLAY_LENGTH = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //set up no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
      /*  Intent intent = getIntent();
        if(intent.hasExtra("flag")) {
            Intent myIntent = new Intent(splash_screen.this, three.class);
            splash_screen.this.startActivity(myIntent);

        }*/
        setContentView(R.layout.activity_splash_screen);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash_screen.this, one.class);
                splash_screen.this.startActivity(mainIntent);
                splash_screen.this.finish();
                setupWindowAnimations();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(500);
        getWindow().setExitTransition(slide);
    }
}
