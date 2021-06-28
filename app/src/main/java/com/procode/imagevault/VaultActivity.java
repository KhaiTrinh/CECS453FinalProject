package com.procode.imagevault;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class VaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (LoginActivity.settings.getBoolean("rememberUser", false))
            FirebaseAuth.getInstance().signOut();
    }
}