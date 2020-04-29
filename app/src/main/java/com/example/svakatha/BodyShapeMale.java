package com.example.svakatha;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BodyShapeMale extends AppCompatActivity {

    private ImageView imageViewBodyShapeScreen,imageview1,imageview2;
    private TextView textViewBodyShapeText2, textViewBodyShapeText3, textViewBodyShapeText4;
    private ProgressBar progressBarBodyShapeScreen;
    private ImageButton imageButtonBody1, imageButtonBody2, imageButtonBody3, imageButtonBody4, imageButtonBody5, imageButtonBody6, imageButtonBodyShapeScreenForward;
    private FirebaseAuth auth;
    private StorageReference mStorage;
    List<Uri> downloadLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_shape_male);
        //casting of ImaegView
        downloadLink=new ArrayList<>();
        imageViewBodyShapeScreen = (ImageView) findViewById(R.id.imageViewBodyShapeScreen_1);
        //casting of textView
        textViewBodyShapeText2 = (TextView) findViewById(R.id.textViewBodyShapeText2_1);
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
        imageButtonBody6 = (ImageButton) findViewById(R.id.imagebuttonbody6);
        //imageview1=findViewById(R.id.imageviewbody1);
        //imageview2=findViewById(R.id.imageviewbody2);


        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        textViewBodyShapeText2.setTypeface(textViewBodyShapeText2.getTypeface(), Typeface.BOLD);
        String name_bodyshape = intent.getStringExtra("Name_skintone");
        textViewBodyShapeText2.setText("Hi"+" "+name_bodyshape);

        /*mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("BodyShapeMale").listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for(StorageReference ref :listResult.getItems()) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadLink.add(uri);
                                }
                            });
                        }
                    }
                });*/


            /*imageview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageview1.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    imageButtonBody5.getBackground().clearColorFilter();
                    imageButtonBody3.getBackground().clearColorFilter();
                    imageButtonBody4.getBackground().clearColorFilter();
                    imageview2.getBackground().clearColorFilter();

                    String bodyshape="Shape1";
                    Picasso.get().load(downloadLink.get(0)).into(imageview1);
                    Map<String, Object> user = new HashMap<>();
                    user.put("BodyShape", bodyshape);
                    db.collection("users").document(currentID).set(user, SetOptions.merge());

                }
            });*/

        /*imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview2.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();
                imageview1.getBackground().clearColorFilter();

                Picasso.get().load(downloadLink.get(1)).into(imageview2);

                String bodyshape="Shape2";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });*/


        imageButtonBody1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody1.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();
                imageButtonBody6.getBackground().clearColorFilter();
                String bodyshape="Shape 1 M";
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
                imageButtonBody6.getBackground().clearColorFilter();

                String bodyshape="Shape 3 M";
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
                imageButtonBody6.getBackground().clearColorFilter();

                String bodyshape="Shape 2 M";
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
                imageButtonBody6.getBackground().clearColorFilter();

                String bodyshape="Shape 4 M";
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
                imageButtonBody6.getBackground().clearColorFilter();

                String bodyshape="Shape 5 M";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBody6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody6.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();
                imageButtonBody5.getBackground().clearColorFilter();

                String bodyshape="Shape 6 M";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBodyShapeScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BodyShapeMale.this,ImageSelection.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent1.putExtra("Name_bodyshape", name_bodyshape);
                startActivity(intent1);
                overridePendingTransition(0,0);

            }
        });

        /*DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewBodyShapeText2.setText("Hi "+finalProfileText);
                        textViewBodyShapeText2.setTypeface(textViewBodyShapeText2.getTypeface(), Typeface.BOLD);
                    }

                });*/
    }
}