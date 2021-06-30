package com.procode.imagevault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.procode.imagevault.R;
import com.procode.imagevault.profile.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME = 1000; // Splash screen will be show for this long

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }, SPLASH_TIME);

    }
}