package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BodyShape extends AppCompatActivity {

    private ImageView imageViewBodyShapeScreen;
    private TextView textViewBodyShapeText2, textViewBodyShapeText3, textViewBodyShapeText4;
    private ProgressBar progressBarBodyShapeScreen;
    private ImageButton imageButtonBody1, imageButtonBody2, imageButtonBody3, imageButtonBody4, imageButtonBody5, imageButtonBodyShapeScreenForward;

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
        //casting of ProgressBar
        progressBarBodyShapeScreen = (ProgressBar) findViewById(R.id.progressBarBodyShapeScreen_1);
        //casting of imageButton
        imageButtonBodyShapeScreenForward = (ImageButton) findViewById(R.id.imageButtonBodyShapeScreenForward_1);
        imageButtonBody1 = (ImageButton) findViewById(R.id.imagebuttonbody1);
        imageButtonBody2 = (ImageButton) findViewById(R.id.imagebuttonbody2);
        imageButtonBody3 = (ImageButton) findViewById(R.id.imagebuttonbody3);
        imageButtonBody4 = (ImageButton) findViewById(R.id.imagebuttonbody4);
        imageButtonBody5 = (ImageButton) findViewById(R.id.imagebuttonbody5);

        imageButtonBody1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonBody1.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                imageButtonBody5.getBackground().clearColorFilter();
                imageButtonBody3.getBackground().clearColorFilter();
                imageButtonBody4.getBackground().clearColorFilter();
                imageButtonBody2.getBackground().clearColorFilter();

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

            }
        });


        imageButtonBodyShapeScreenForward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ImageSelection.class));
            }
        });
    }
}

