package com.example.isaachutchinson1.highlanderbuy;
/**
 * @author Isaac Hutchinson
 * @version 1.0
 * <p>
 * Camera and image storage from google developer.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddNewListing extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button addImageBtn;
    private ImageView newListingPreview;
    private Button createListingBtn;
    private TextInputLayout titleInput;
    private TextInputLayout descInput;

    private StorageReference mStorageRef;

    /**
     * The onCreate method is the main control for the activity and generates the information and
     * content to the activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_listing);

        addImageBtn = (Button) findViewById(R.id.addNewListing_addBtn);
        createListingBtn = (Button) findViewById(R.id.addNewListing_CreateBtn);
        titleInput = (TextInputLayout) findViewById(R.id.addNewListing_Title);
        descInput = (TextInputLayout) findViewById(R.id.addNewListing_Desc);
        newListingPreview = (ImageView) findViewById(R.id.newListing_preview);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });


        createListingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    /**
     * The onActivityResult method waits for the camera intent action to return information
     * from the user taking a photo. Then the photo is stored into the activity.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            newListingPreview.setImageBitmap(imageBitmap);
        }
    }

}
