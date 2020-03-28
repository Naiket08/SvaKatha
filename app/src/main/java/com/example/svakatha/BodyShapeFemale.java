package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BodyShapeFemale extends AppCompatActivity {

    private ImageView imageViewBodyShapeScreenF,imageview1,imageview2F;
    private TextView textViewBodyShapeText2F, textViewBodyShapeText3F, textViewBodyShapeText4F;
    private ProgressBar progressBarBodyShapeScreenF;
    private ImageButton imageButtonBody1F, imageButtonBody2F, imageButtonBody3F, imageButtonBody4F, imageButtonBody5F, imageButtonBodyShapeScreenForwardF;
    private FirebaseAuth auth;
    private StorageReference mStorage;
    List<Uri> downloadLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_shape_female);
        //casting of ImaegView
        downloadLink=new ArrayList<>();
        imageViewBodyShapeScreenF = (ImageView) findViewById(R.id.imageViewBodyShapeScreen_1_F);
        //casting of textView
        textViewBodyShapeText2F = (TextView) findViewById(R.id.textViewBodyShapeText2_1_F);
        textViewBodyShapeText3F = (TextView) findViewById(R.id.textViewBodyShapeText3_1_F);
        textViewBodyShapeText4F = (TextView) findViewById(R.id.textViewBodyShapeText4_1_F);
        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarBodyShapeScreen_1_F);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 50,70);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //casting of imageButton
        imageButtonBodyShapeScreenForwardF = (ImageButton) findViewById(R.id.imageButtonBodyShapeScreenForward_1_F);
        imageButtonBody1F = (ImageButton) findViewById(R.id.imagebuttonbody1F);
        imageButtonBody2F = (ImageButton) findViewById(R.id.imagebuttonbody2F);
        imageButtonBody3F = (ImageButton) findViewById(R.id.imagebuttonbody3F);
        imageButtonBody4F = (ImageButton) findViewById(R.id.imagebuttonbody4F);
        imageButtonBody5F = (ImageButton) findViewById(R.id.imagebuttonbody5F);
        //imageview1=findViewById(R.id.imageviewbody1);
        //imageview2=findViewById(R.id.imageviewbody2);


        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        textViewBodyShapeText2F.setTypeface(textViewBodyShapeText2F.getTypeface(), Typeface.BOLD);
        String name_bodyshapeF = intent.getStringExtra("Name_skintone");
        textViewBodyShapeText2F.setText("Hi"+" "+name_bodyshapeF);

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


        imageButtonBody1F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody1F.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5F.getBackground().clearColorFilter();
                imageButtonBody3F.getBackground().clearColorFilter();
                imageButtonBody4F.getBackground().clearColorFilter();
                imageButtonBody2F.getBackground().clearColorFilter();
                String bodyshape="Shape 1 F";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBody3F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody3F.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1F.getBackground().clearColorFilter();
                imageButtonBody2F.getBackground().clearColorFilter();
                imageButtonBody5F.getBackground().clearColorFilter();
                imageButtonBody4F.getBackground().clearColorFilter();

                String bodyshape="Shape 3 F";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonBody2F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody2F.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5F.getBackground().clearColorFilter();
                imageButtonBody3F.getBackground().clearColorFilter();
                imageButtonBody4F.getBackground().clearColorFilter();
                imageButtonBody1F.getBackground().clearColorFilter();

                String bodyshape="Shape 2 F";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBody4F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody4F.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1F.getBackground().clearColorFilter();
                imageButtonBody2F.getBackground().clearColorFilter();
                imageButtonBody3F.getBackground().clearColorFilter();
                imageButtonBody5F.getBackground().clearColorFilter();

                String bodyshape="Shape 4 F";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonBody5F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody5F.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody1F.getBackground().clearColorFilter();
                imageButtonBody2F.getBackground().clearColorFilter();
                imageButtonBody3F.getBackground().clearColorFilter();
                imageButtonBody4F.getBackground().clearColorFilter();

                String bodyshape="Shape 5 F";
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonBodyShapeScreenForwardF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BodyShapeFemale.this,ImageSelection.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent1.putExtra("Name_bodyshape", name_bodyshapeF);
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