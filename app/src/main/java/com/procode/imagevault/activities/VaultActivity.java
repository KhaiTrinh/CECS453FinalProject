package com.procode.imagevault.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

    // Components
    private FloatingActionButton fab;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);


        // Instantiate the Firebase Storage and Database
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        fab = findViewById(R.id.fabUpload);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });
    }

    //Nikko: I'm thinking that we should have this here because the app stops when the user has to go to another app to get their image
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (LoginActivity.settings.getBoolean("rememberUser", false))
//            FirebaseAuth.getInstance().signOut();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects Request Codes
        if(requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
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

            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}