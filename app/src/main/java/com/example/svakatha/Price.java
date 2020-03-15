package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class Price extends AppCompatActivity {
    private ImageButton imgb1 =(ImageButton)findViewById(R.id.imageButtonDetailsScreenForward_1);


    TextView textViewDetailsScreenGreet, textViewDetailsScreenText2, textViewDetailsScreenText3, textViewDetailsScreenOccupation;
    private ImageView imageViewDetailsScreenHeader;
    private EditText editTextDetailsScreenStyle;
    private ImageButton imageButtonDetailsScreenForward;
    private ProgressBar progressBarDetailsScreen;
    private SeekBar seekBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);
        textViewDetailsScreenText2 = (TextView) findViewById(R.id.textViewDetailsScreenText2);
        textViewDetailsScreenText3 = (TextView) findViewById(R.id.textViewDetailsScreenText3);
        textViewDetailsScreenOccupation = (TextView) findViewById(R.id.textViewDetailsScreenOccupation);

        imageButtonDetailsScreenForward = (ImageButton) findViewById(R.id.imageButtonDetailsScreenForward);

        editTextDetailsScreenStyle = (EditText) findViewById(R.id.editTextDetailsScreenStyle);

        imageViewDetailsScreenHeader = (ImageView) findViewById(R.id.imageViewDetailsScreenHeader);

        progressBarDetailsScreen = (ProgressBar) findViewById(R.id.progressBarDetailsScreen);

        imgb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Style.class));
            }
        });
    }
}
