package com.procode.imagevault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.procode.imagevault.R;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_SETTINGS_FILE_NAME = "login_settings";
    private SharedPreferences settings;
    private FirebaseAuth mAuth;

    private EditText mEmail, mPassword;
    private Button mLogin;
    private CheckBox mRememberMe;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        // Grab settings file
        settings = getApplicationContext().getSharedPreferences(LOGIN_SETTINGS_FILE_NAME, MODE_PRIVATE);
        // If it's new set remember user to false
        if (!settings.contains("rememberUser")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("rememberUser", false);
            editor.apply();
        }
        // Transition immediately if remember me is true
        if (settings.getBoolean("rememberUser", true)) {
            Intent intent = new Intent(getApplicationContext(), VaultActivity.class);
            startActivity(intent);
        }

        mEmail = findViewById(R.id.etRegisterEmail);
        mPassword = findViewById(R.id.etLoginPassword);
        mLogin = findViewById(R.id.btnLogin);
        mRememberMe = findViewById(R.id.cbRememberMe);
        mRegister = findViewById(R.id.tvRegister);

        // Sets all the click listeners for this activity
        setOnClickListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void setOnClickListeners() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean correctCredentials = false;
                // Code to check credentials goes here

                if (correctCredentials) {
                    Intent intent = new Intent(getApplicationContext(), VaultActivity.class);
                    startActivity(intent);
                } else {
                    // Clear password field if entered information is incorrect
                    mPassword.getText().clear();
                    Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        mRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("rememberUser", isChecked);
                editor.apply();
            }
        });
    }
}