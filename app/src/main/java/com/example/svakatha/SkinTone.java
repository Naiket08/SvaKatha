package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class SkinTone extends AppCompatActivity {

    private ImageView imageViewSkinToneScreenToneHeader;
    private TextView textViewSkinToneScreenGreet,textViewSkinToneScreenText2,textViewSkinToneScreenText3,textViewSkinTone;
    private ImageButton imageButtonSkinToneScreenForward,imageButtonImage1,imageButtonImage2,imageButtonImage3,imageButtonImage4,imageButtonImage5,imageButtonImage6,imageButtonImage7,imageButtonImage8,imageButtonImage9,imageButtonImage10,imageButtonImage11,imageButtonImage12,imageButtonImage13,imageButtonImage14,imageButtonImage15,imageButtonImage16,imageButtonImage17,imageButtonImage18,imageButtonImage19,imageButtonImage20,imageButtonImage21,imageButtonImage22,imageButtonImage23,imageButtonImage24,imageButtonImage25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_tone);
        //casting of imageview
        imageViewSkinToneScreenToneHeader=(ImageView)findViewById(R.id.imageViewSkinToneScreenHeader_1);
        //casting of textView
        textViewSkinToneScreenGreet=(TextView)findViewById(R.id.textViewSkinToneScreenGreet_1);
        textViewSkinToneScreenText2=(TextView)findViewById(R.id.textViewUserScreenText2_1);
        textViewSkinToneScreenText3=(TextView)findViewById(R.id.textViewSkinToneScreenText3_1);
        textViewSkinTone=(TextView)findViewById(R.id.skintone);
        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarSkinTone);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 40,50);
        progressAnimator.setDuration(900);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        //casting of imagebutton
        imageButtonSkinToneScreenForward=(ImageButton)findViewById(R.id.imageButtonSkinToneScreenForward_1);
        imageButtonSkinToneScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SkinTone.this,BodyShape.class);
                startActivity(intent1);

            }
        });
        imageButtonImage1=(ImageButton)findViewById(R.id.imageButtonimg_1);
        imageButtonImage2=(ImageButton)findViewById(R.id.imageButtonimg_2);
        imageButtonImage3=(ImageButton)findViewById(R.id.imageButtonimg_3);
        imageButtonImage4=(ImageButton)findViewById(R.id.imageButtonimg_4);
        imageButtonImage5=(ImageButton)findViewById(R.id.imageButtonimg_5);
        imageButtonImage6=(ImageButton)findViewById(R.id.imageButtonimg_6);
        imageButtonImage7=(ImageButton)findViewById(R.id.imageButtonimg_7);
        imageButtonImage8=(ImageButton)findViewById(R.id.imageButtonimg_8);
        imageButtonImage9=(ImageButton)findViewById(R.id.imageButtonimg_9);
        imageButtonImage10 =(ImageButton)findViewById(R.id.imageButtonimg_10);
        imageButtonImage11=(ImageButton)findViewById(R.id.imageButtonimg_11);
        imageButtonImage12=(ImageButton)findViewById(R.id.imageButtonimg_12);
        imageButtonImage13=(ImageButton)findViewById(R.id.imageButtonimg_13);
        imageButtonImage14=(ImageButton)findViewById(R.id.imageButtonimg_14);
        imageButtonImage15=(ImageButton)findViewById(R.id.imageButtonimg_15);
        imageButtonImage16=(ImageButton)findViewById(R.id.imageButtonimg_16);
        imageButtonImage17=(ImageButton)findViewById(R.id.imageButtonimg_17);
        imageButtonImage18=(ImageButton)findViewById(R.id.imageButtonimg_18);
        imageButtonImage19=(ImageButton)findViewById(R.id.imageButtonimg_19);
        imageButtonImage20=(ImageButton)findViewById(R.id.imageButtonimg_20);
        imageButtonImage21=(ImageButton)findViewById(R.id.imageButtonimg_21);
        imageButtonImage22=(ImageButton)findViewById(R.id.imageButtonimg_22);
        imageButtonImage23=(ImageButton)findViewById(R.id.imageButtonimg_23);
        imageButtonImage24=(ImageButton)findViewById(R.id.imageButtonimg_24);
        imageButtonImage25=(ImageButton)findViewById(R.id.imageButtonimg_25);

        imageButtonImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage1.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type1";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage2.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type2";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage3.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type3";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });

        imageButtonImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage4.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type4";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage5.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type5";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage6.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type6";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());



            }
        });
        imageButtonImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage7.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type7";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage8.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type8";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage9.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type9";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage10.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type10";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage11.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type11";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());



            }
        });
        imageButtonImage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage12.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type12";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage13.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();
                String skintone="Type13";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage14.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type14";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage15.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type15";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());


            }
        });
        imageButtonImage16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage16.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type16";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage17.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type17";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage18.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type18";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());
            }
        });
        imageButtonImage19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage19.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type19";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage20.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type20";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage21.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type21";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage22.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type22";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());
            }
        });
        imageButtonImage23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage23.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type23";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage24.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage25.getBackground().clearColorFilter();

                String skintone="Type24";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });
        imageButtonImage25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage25.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonImage1.getBackground().clearColorFilter();
                imageButtonImage2.getBackground().clearColorFilter();
                imageButtonImage3.getBackground().clearColorFilter();
                imageButtonImage4.getBackground().clearColorFilter();
                imageButtonImage5.getBackground().clearColorFilter();
                imageButtonImage6.getBackground().clearColorFilter();
                imageButtonImage7.getBackground().clearColorFilter();
                imageButtonImage8.getBackground().clearColorFilter();
                imageButtonImage9.getBackground().clearColorFilter();
                imageButtonImage10.getBackground().clearColorFilter();
                imageButtonImage11.getBackground().clearColorFilter();
                imageButtonImage12.getBackground().clearColorFilter();
                imageButtonImage13.getBackground().clearColorFilter();
                imageButtonImage14.getBackground().clearColorFilter();
                imageButtonImage15.getBackground().clearColorFilter();
                imageButtonImage16.getBackground().clearColorFilter();
                imageButtonImage17.getBackground().clearColorFilter();
                imageButtonImage18.getBackground().clearColorFilter();
                imageButtonImage19.getBackground().clearColorFilter();
                imageButtonImage20.getBackground().clearColorFilter();
                imageButtonImage21.getBackground().clearColorFilter();
                imageButtonImage22.getBackground().clearColorFilter();
                imageButtonImage23.getBackground().clearColorFilter();
                imageButtonImage24.getBackground().clearColorFilter();

                String skintone="Type25";
                Map<String, Object> user = new HashMap<>();
                user.put("SkinTone", skintone);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

            }
        });

        DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewSkinToneScreenGreet.setText("Hi "+finalProfileText);
                        textViewSkinToneScreenGreet.setTypeface(textViewSkinToneScreenGreet.getTypeface(), Typeface.BOLD);
                    }

                });
    }
}
