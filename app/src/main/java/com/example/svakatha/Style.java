package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Style extends AppCompatActivity {

    private ImageView imageViewDetailsScreenHeader;
    private TextView textViewDetailsScreenGreet,textViewDetailsScreenText2,textViewDetailsScreenText3,textViewDetailsScreenOccupation;
    private EditText editTextDetailsScreenStyle_3;
    private ImageButton imageButtonDetailsScreenForward_3;
    private ProgressBar progressBarDetailsScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        //casting of textview
        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);
        textViewDetailsScreenText2=(TextView)findViewById(R.id.textViewDetailsScreenText2);
        textViewDetailsScreenText3=(TextView)findViewById(R.id.textViewDetailsScreenText3);
        textViewDetailsScreenOccupation=(TextView)findViewById(R.id.textViewDetailsScreenOccupation);

        //casting of ImageView
        imageViewDetailsScreenHeader=(ImageView)findViewById(R.id.imageViewDetailsScreenHeader);

        //casting of ProgressBar
        progressBarDetailsScreen =(ProgressBar)findViewById(R.id.progressBarDetailsScreen);

        //casting of EditText
        editTextDetailsScreenStyle_3=(EditText)findViewById(R.id.editTextDetailsScreenStyle_3);

        //casting of ImageButton
        imageButtonDetailsScreenForward_3=(ImageButton)findViewById(R.id.imageButtonDetailsScreenForward_3);


        imageButtonDetailsScreenForward_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Business.class));
            }
        });
    }
}
