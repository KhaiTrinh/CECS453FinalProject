package com.procode.imagevault.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.procode.imagevault.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class VaultActivity extends AppCompatActivity {
    private static final String TAG = VaultActivity.class.getSimpleName();

    // REQUEST_CODE
    private static final int GET_FROM_GALLERY = 1;

    // Firebase Related
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    // Shared Preference
    private SharedPreferences settings;

    // Components
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        System.out.println("onCreate is called!");

        // Instantiate the Firebase Storage and Database
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        settings = getApplicationContext().getSharedPreferences(LoginActivity.LOGIN_SETTINGS_FILE_NAME, MODE_PRIVATE);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        fab = findViewById(R.id.fabUpload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("rememberUser", false);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.vaultToolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("onActivityResult is called!");

        //Detects Request Codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] image_data = baos.toByteArray();

                // Stores the image under the path
                String path = FirebaseAuth.getInstance().getUid() + "/" + UUID.randomUUID() + ".png";
                storageReference = storage.getReference(path);

                //Uploads the image w/metadata to the Storage with the storage reference
                UploadTask uploadTask = storageReference.putBytes(image_data);
                uploadTask.addOnProgressListener(VaultActivity.this, new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot snapshot) {
                        progressBar.setVisibility(View.VISIBLE);
                        fab.setVisibility(View.INVISIBLE);
                        Toast.makeText(VaultActivity.this, "Image Uploading...Please Wait", Toast.LENGTH_SHORT).show();
                    }
                });

                uploadTask.addOnSuccessListener(VaultActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressBar.setVisibility(View.INVISIBLE);
                        fab.setVisibility(View.VISIBLE);
                        Uri url = taskSnapshot.getUploadSessionUri();
                        Toast.makeText(VaultActivity.this, "Successfully Uploaded Image", Toast.LENGTH_LONG).show();
                    }
                });

                uploadTask.addOnFailureListener(VaultActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(VaultActivity.this, "Failed to Upload...", Toast.LENGTH_LONG).show();
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vault, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println("onOptionsItemSelected is called");
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        boolean remember = settings.getBoolean("rememberUser", true);
        if (!remember)
            FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }
}