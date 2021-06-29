/*
CECS 453-01 Final Project
Authors: Nikko Chan & Khai Trinh
Due Date: July 1, 2021
Description: This class display the images inside
the current user's directory for images on
the Firebase server.
*/

package com.procode.imagevault.vault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.procode.imagevault.R;
import com.procode.imagevault.profile.LoginActivity;
import com.procode.imagevault.upload.Upload;
import com.procode.imagevault.upload.UploadActivity;

import java.util.ArrayList;
import java.util.List;

public class VaultActivity extends AppCompatActivity {

    // Shared Preference
    private SharedPreferences mSettings;

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        mSettings = getApplicationContext().getSharedPreferences(LoginActivity.LOGIN_SETTINGS_FILE_NAME, MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.vaultToolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        String path = "images/" + FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(path);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(VaultActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(VaultActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Components
        FloatingActionButton fab = findViewById(R.id.fabUpload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(intent);
            }
        });
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
}