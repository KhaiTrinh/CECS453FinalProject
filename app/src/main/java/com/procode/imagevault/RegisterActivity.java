package com.procode.imagevault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail, mPassword, mConfirmation;
    private Button mSubmit;

    private final String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
    private AwesomeValidation mValidator;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mValidator = new AwesomeValidation(ValidationStyle.BASIC);
        mAuth = FirebaseAuth.getInstance();
        setValidators();

        mEmail = findViewById(R.id.etRegisterEmail);
        mPassword = findViewById(R.id.etRegisterPassword);
        mConfirmation = findViewById(R.id.etPasswordConfirmation);

        mSubmit = findViewById(R.id.btnSubmit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert user inputs into Strings
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (mValidator.validate()) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(RegisterActivity.this, "Account successfully created!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    mEmail.getText().clear();
                                    mPassword.getText().clear();
                                    mConfirmation.getText().clear();
                                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        // Calling the action bar
        Toolbar toolbar = findViewById(R.id.registerToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // This event will enable the back function to the button on press
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setValidators() {
        mValidator.addValidation(this, R.id.etRegisterEmail, Patterns.EMAIL_ADDRESS, R.string.err_email);
        mValidator.addValidation(this, R.id.etRegisterPassword, regexPassword, R.string.err_password);
        mValidator.addValidation(this, R.id.etPasswordConfirmation, R.id.etRegisterPassword, R.string.err_password_confirmation);
    }
}