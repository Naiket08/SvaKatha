package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView splash1;
    TextView splash2;
    TextView splash3;
    ImageView logo,logolast,logofirst,logos,logov,logoa,logok,logoa1,logot,logoh,logoa2;
    Animation animation,animation1,animation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        splash2 = (TextView)findViewById(R.id.splash2);
        splash3 = (TextView)findViewById(R.id.splash3);
        //logo = (ImageView)findViewById(R.id.logo);
        logofirst =(ImageView)findViewById(R.id.logo_first);
        logolast = (ImageView)findViewById(R.id.logo_last);
       /* logos = (ImageView)findViewById(R.id.logo_s);
        logov = (ImageView)findViewById(R.id.logo_v);
        logoa = (ImageView)findViewById(R.id.logo_a);
        logok = (ImageView)findViewById(R.id.logo_k);
        logoa1 = (ImageView)findViewById(R.id.logo_a1);
        logot = (ImageView)findViewById(R.id.logo_t);
        logoh = (ImageView)findViewById(R.id.logo_h);
        logoa2 = (ImageView)findViewById(R.id.logo_a2);*/
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_animation);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_animation_side);
//        animation3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_s);
//        logos.startAnimation(animation3);
//        logov.startAnimation(animation3);
//        logoa.startAnimation(animation3);
//        logok.startAnimation(animation3);
//        logoa1.startAnimation(animation3);
//        logot.startAnimation(animation3);
//        logoh.startAnimation(animation3);
//        logoa2.startAnimation(animation3);

        logofirst.startAnimation(animation);
        logolast.startAnimation(animation1);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, HostActivity.class);
                startActivity(i);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        },3000);
    }
}
