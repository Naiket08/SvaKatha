package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

public class DetailsScreen extends AppCompatActivity {

    TextView textViewDetailsScreenGreet, textViewDetailsScreenText2, textViewDetailsScreenText3, textViewDetailsScreenOccupation;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private ImageView imageViewDetailsScreenHeader;
    private EditText editTextDetailsScreenStyle;
    private ImageButton imageButtonDetailsScreenForward;
    private ProgressBar progressBarDetailsScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);

        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);
        textViewDetailsScreenText2 = (TextView) findViewById(R.id.textViewDetailsScreenText2);
        textViewDetailsScreenText3 = (TextView) findViewById(R.id.textViewDetailsScreenText3);
        textViewDetailsScreenOccupation = (TextView) findViewById(R.id.textViewDetailsScreenOccupation);

        imageButtonDetailsScreenForward = (ImageButton) findViewById(R.id.imageButtonDetailsScreenForward);

        editTextDetailsScreenStyle = (EditText) findViewById(R.id.editTextDetailsScreenStyle);

        imageViewDetailsScreenHeader = (ImageView) findViewById(R.id.imageViewDetailsScreenHeader);

        progressBarDetailsScreen = (ProgressBar) findViewById(R.id.progressBarDetailsScreen);

        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);
        imageButtonDetailsScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextDetailsScreenStyle.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), Price.class));
                }
            }
        });

    }

}