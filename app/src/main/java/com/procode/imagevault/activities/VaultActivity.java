/*
CECS 453-01 Final Project
Authors: Nikko Chan & Khai Trinh
Due Date: July 1, 2021
Description: This class display the images inside
the current user's directory for images on
the Firebase server.
*/

package com.procode.imagevault.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.procode.imagevault.R;


public class VaultActivity extends AppCompatActivity {

    // Shared Preference
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        mSettings = getApplicationContext().getSharedPreferences(LoginActivity.LOGIN_SETTINGS_FILE_NAME, MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.vaultToolbar);
        setSupportActionBar(toolbar);

        // Components
        FloatingActionButton fab = findViewById(R.id.fabUpload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(intent);
            }
        });

        // To be deleted after testing
        TextView mUserInfo = findViewById(R.id.tvUserInfo);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String info = "Email: " + user.getEmail() + "\nUid: " + user.getUid();
        mUserInfo.setText(info);
        // To be deleted after testing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vault, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putBoolean("rememberUser", false);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        boolean remember = mSettings.getBoolean("rememberUser", true);
        if (!remember) {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putBoolean("rememberUser", false);
            editor.apply();
        }
        super.onStop();
    }
}