package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
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
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class BodyShape extends AppCompatActivity {

    private ImageView imageViewBodyShapeScreen;
    private TextView textViewBodyShapeText2, textViewBodyShapeText3, textViewBodyShapeText4;
    private ProgressBar progressBarBodyShapeScreen;
    private ImageButton imageButtonBody1, imageButtonBody2, imageButtonBody3, imageButtonBody4, imageButtonBody5, imageButtonBodyShapeScreenForward;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_shape);
        //casting of ImaegView
        imageViewBodyShapeScreen = (ImageView) findViewById(R.id.imageViewBodyShapeScreen_1);
        //casting of textView
        textViewBodyShapeText2 = (TextView) findViewById(R.id.textViewBodyShapeText2_1);
        textViewBodyShapeText3 = (TextView) findViewById(R.id.textViewBodyShapeText3_1);
        textViewBodyShapeText4 = (TextView) findViewById(R.id.textViewBodyShapeText4_1);
        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarBodyShapeScreen_1);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 50,70);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //casting of imageButton
        imageButtonBodyShapeScreenForward = (ImageButton) findViewById(R.id.imageButtonBodyShapeScreenForward_1);
        imageButtonBody1 = (ImageButton) findViewById(R.id.imagebuttonbody1);
        imageButtonBody2 = (ImageButton) findViewById(R.id.imagebuttonbody2);
        imageButtonBody3 = (ImageButton) findViewById(R.id.imagebuttonbody3);
        imageButtonBody4 = (ImageButton) findViewById(R.id.imagebuttonbody4);
        imageButtonBody5 = (ImageButton) findViewById(R.id.imagebuttonbody5);

        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        imageButtonBody1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody1.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();
                String bodyshape="Shape1";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBody3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody3.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();
                imageButtonBody5.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();

                String bodyshape="Shape3";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonBody2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody2.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();
                imageButtonBody1.getBackground().clearColorFilter();

                String bodyshape="Shape2";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBody4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody4.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody5.getBackground().clearColorFilter();

                String bodyshape="Shape4";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonBody5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody5.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();

                String bodyshape="Shape5";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });


        imageButtonBodyShapeScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BodyShape.this,ImageSelection.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewBodyShapeText2.setText("Hi "+finalProfileText);
                        textViewBodyShapeText2.setTypeface(textViewBodyShapeText2.getTypeface(), Typeface.BOLD);
                    }

                });
    }
}