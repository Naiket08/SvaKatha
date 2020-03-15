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

public class Business extends AppCompatActivity {

    private ImageView imageViewDetailsScreenHeader;
    private TextView textViewDetailsScreenGreet,textViewDetailsScreenText2,textViewDetailsScreenText3,textViewDetailsScreenOccupation;
    private EditText editTextDetailsScreenStyle_4;
    private ImageButton imageButtonDetailsScreenForward_4;
    private ProgressBar progressBarDetailsScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        //casting of TextView
        textViewDetailsScreenGreet=(TextView)findViewById(R.id.textViewDetailsScreenGreet);
        textViewDetailsScreenText2=(TextView)findViewById(R.id.textViewDetailsScreenText2);
        textViewDetailsScreenText3=(TextView)findViewById(R.id.textViewDetailsScreenText3);
        textViewDetailsScreenOccupation=(TextView)findViewById(R.id.textViewDetailsScreenOccupation);

        //casting of ImageView
        imageViewDetailsScreenHeader=(ImageView)findViewById(R.id.imageViewDetailsScreenHeader);

        //casting of EditText
        editTextDetailsScreenStyle_4=(EditText)findViewById(R.id.editTextDetailsScreenStyle_4);

        //casting of ImageButton
        imageButtonDetailsScreenForward_4=(ImageButton)findViewById(R.id.imageButtonDetailsScreenForward_4);

        //casting of ProgressBar
        progressBarDetailsScreen =(ProgressBar)findViewById(R.id.progressBarDetailsScreen);
        

    }
}
