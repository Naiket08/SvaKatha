package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ImageSelection extends AppCompatActivity {

    private ImageView  imageViewImageSelectionScreenHeader,imageViewPerson;
    private TextView textViewImageSelectionText,textViewImageSelectionText2,textViewImageSelectionText3,textViewImageSelectionHate,textViewImageSelectionNotSure,textViewImaegSelectionLove;
    private ImageButton imageButtonImageSelectionHate,imageButtonImageSelectionNotSure,imageButtonImageSelectionLove,imageButtonImageSelectionScreenForward;
    private ProgressBar progressBarImageSelectionScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        //casting of ImageView
        imageViewImageSelectionScreenHeader=(ImageView)findViewById(R.id.imageViewImageSelectionScreenHeader_1);
        imageViewPerson=(ImageView)findViewById(R.id.imageViewPerson_1);
        //casting of TextView
        textViewImageSelectionText=(TextView)findViewById(R.id.textViewImageSelectionText_1);
        textViewImageSelectionText2=(TextView)findViewById(R.id.textViewImageSelectionText2_1);
        textViewImageSelectionText3=(TextView)findViewById(R.id.textViewImageSelectionText3_1);
        textViewImageSelectionHate=(TextView)findViewById(R.id.textviewimageselectionHate_1);
        textViewImageSelectionNotSure=(TextView)findViewById(R.id.textviewimageselectionNotSure_1);
        textViewImaegSelectionLove=(TextView)findViewById(R.id.textviewimageselectionLove_1);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //casting of ImageButton
        imageButtonImageSelectionHate=(ImageButton)findViewById(R.id.imagebuttonimageselectionHate_1);
        imageButtonImageSelectionNotSure=(ImageButton)findViewById(R.id.imagebuttonimageselectionNotSure_1);
        imageButtonImageSelectionLove=(ImageButton)findViewById(R.id.imagebuttonimageselectionLove_1);
        imageButtonImageSelectionScreenForward=(ImageButton)findViewById(R.id.imageButtonimageSelectionScreenForward_1);
        //casting of ProgressBar
        progressBarImageSelectionScreen=(ProgressBar)findViewById(R.id.progressBarImageSelectionScreen_1);


      /*  imageButtonImageSelectionScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        */
      imageButtonImageSelectionHate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "Image is deleted", Toast.LENGTH_SHORT).show();
              imageButtonImageSelectionHate.isPressed();
          }
      });
      imageButtonImageSelectionNotSure.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "not sure about this image", Toast.LENGTH_SHORT).show();
              imageButtonImageSelectionNotSure.isPressed();
          }
      });
      imageButtonImageSelectionLove.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "image is inserted", Toast.LENGTH_SHORT).show();
          }
      });
      imageButtonImageSelectionScreenForward.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "Next Page", Toast.LENGTH_SHORT).show();
          }
      });

        DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        String currentID = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewImageSelectionText.setText("Hi "+finalProfileText);

                    }

                });

    }
}
