package com.procode.imagevault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.procode.imagevault.R;

public class VaultActivity extends AppCompatActivity {
    //REQUEST_CODES
    private static int GET_FROM_GALLERY = 0;

    // Firebase Related
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    // Shared Preference
    private SharedPreferences settings;

    // Components
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        settings = getApplicationContext().getSharedPreferences(LoginActivity.LOGIN_SETTINGS_FILE_NAME, MODE_PRIVATE);

        fab = findViewById(R.id.fabUpload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        boolean remember = settings.getBoolean("rememberUser", true);
        if (!remember) FirebaseAuth.getInstance().signOut();
        super.onStop();
    }
}