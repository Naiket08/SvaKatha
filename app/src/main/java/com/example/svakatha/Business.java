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

    private ImageView imageViewBusinessScreenHeader;
    private TextView textViewBusinessScreenGreet,textViewBusinessScreenText2,textViewBusinessScreenText3,textViewBusinessScreenOccasion;
    private EditText editTextBusinessScreen;
    private ImageButton imageButtonBusinessScreenForward;
    private ProgressBar progressBarBusinessScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        //casting of TextView
        textViewBusinessScreenGreet=(TextView)findViewById(R.id.textViewBusinessScreenGreet1);
        textViewBusinessScreenText2=(TextView)findViewById(R.id.textViewBusinessScreenText2_1);
        textViewBusinessScreenText3=(TextView)findViewById(R.id.textViewBusinessScreenText3_1);
        textViewBusinessScreenOccasion=(TextView)findViewById(R.id.textViewBusinessScreenOccasion_1);

        //casting of ImageView
        imageViewBusinessScreenHeader=(ImageView)findViewById(R.id.imageViewBusinessScreenHeader1);

        //casting of EditText
        editTextBusinessScreen=(EditText)findViewById(R.id.editTextBusinessScreen_1);

        //casting of ImageButton
        imageButtonBusinessScreenForward=(ImageButton)findViewById(R.id.imageButtonBusinessScreenForward_1);

        //casting of ProgressBar
        progressBarBusinessScreen =(ProgressBar)findViewById(R.id.progressBarBusinessScreen1);
        
        imageButtonBusinessScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Part_two.class));
            }
        });
    }
}
