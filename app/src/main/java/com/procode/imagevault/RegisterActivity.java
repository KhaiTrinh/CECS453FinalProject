package com.procode.imagevault;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail, mPassword, mConfirmation;
    private Button mSubmit;

    private AwesomeValidation mValidator;
    private final String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mValidator = new AwesomeValidation(ValidationStyle.BASIC);
        setValidator();

        mEmail = findViewById(R.id.etRegisterEmail);
        mPassword = findViewById(R.id.etRegisterPassword);
        mConfirmation = findViewById(R.id.etPasswordConfirmation);

        mSubmit = findViewById(R.id.btnSubmit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for adding credentials goes here

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
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

    private void setValidator() {
        mValidator.addValidation(this, R.id.etRegisterEmail, Patterns.EMAIL_ADDRESS, R.string.err_email);
        mValidator.addValidation(this, R.id.etRegisterPassword, regexPassword, R.string.err_password);
        mValidator.addValidation(this, R.id.etPasswordConfirmation, R.id.etRegisterPassword, R.string.err_password_confirmation);
    }
}