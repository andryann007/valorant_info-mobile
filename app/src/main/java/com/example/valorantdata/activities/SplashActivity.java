package com.example.valorantdata.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.valorantdata.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //start main screen after 2seconds
        new Handler().postDelayed(this::main_menu,2500);//2500 means 2.5 seconds
    }

    private void main_menu() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}