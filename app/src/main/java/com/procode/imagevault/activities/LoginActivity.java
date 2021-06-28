package com.procode.imagevault.activities;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.procode.imagevault.R;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_SETTINGS_FILE_NAME = "login_settings";
    public static SharedPreferences settings;
    private FirebaseAuth mAuth;

    private EditText mEmail, mPassword;
    private Button mLogin;
    private CheckBox mRememberMe;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Grab settings file
        settings = getApplicationContext().getSharedPreferences(LOGIN_SETTINGS_FILE_NAME, MODE_PRIVATE);
        // If it's new set remember user to false
        if (!settings.contains("rememberUser")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("rememberUser", false);
            editor.apply();
        }

        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.etLoginEmail);
        mPassword = findViewById(R.id.etLoginPassword);
        mLogin = findViewById(R.id.btnLogin);
        mRememberMe = findViewById(R.id.cbRememberMe);
        mRegister = findViewById(R.id.tvRegister);

        // Sets all the click listeners for this activity
        setOnClickListeners();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null && settings.getBoolean("rememberUser", false)) { // User is already signed in
//            FirebaseAuth.getInstance().signOut();
//        } else if (currentUser != null && settings.getBoolean("rememberUser", true)) {
//            Intent intent = new Intent(getApplicationContext(), VaultActivity.class);
//            startActivity(intent);
//        }
//    }

    private void setOnClickListeners() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(getApplicationContext(), VaultActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Clear password field if entered information is incorrect
                                mPassword.getText().clear();
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
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