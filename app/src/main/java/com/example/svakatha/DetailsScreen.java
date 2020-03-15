package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class DetailsScreen extends AppCompatActivity {

    TextView textViewDetailsScreenGreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textViewDetailsScreenGreet = (TextView)findViewById(R.id.textViewDetailsScreenGreet);
        Intent intent = getIntent();
        String greetname = intent.getStringExtra("Name");
        textViewDetailsScreenGreet.setTypeface(textViewDetailsScreenGreet.getTypeface(), Typeface.BOLD);
        textViewDetailsScreenGreet.setText("Hi"+" "+greetname);
    }
}
