package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //casting of imagebutton
        imageButtonSkinToneScreenForward=(ImageButton)findViewById(R.id.imageButtonSkinToneScreenForward_1);
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

        imageButtonSkinToneScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BodyShape.class));
            }
        });

        imageButtonImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage1.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage2.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage3.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage1.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage4.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage5.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage6.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage7.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage8.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage9.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage10.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage11.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage12.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage13.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage14.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage15.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage16.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage17.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage18.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage19.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage20.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage21.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage22.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage23.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage24.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

            }
        });
        imageButtonImage25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonImage25.getBackground().setColorFilter(0xfff47521, PorterDuff.Mode.SRC_ATOP);

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
                        textViewSkinToneScreenGreet.setText("Hi "+finalProfileText);

                    }

                });
    }
}
