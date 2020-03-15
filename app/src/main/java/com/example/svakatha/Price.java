package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.ImageReader;
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

import org.w3c.dom.Text;

public class Price extends AppCompatActivity {

    private ImageView imageViewDetailScreenHeader;
    private TextView textViewDetailsScreenGreet1,textViewDetailsScreenTextTwo2_1,getTextViewDetailsScreenTextTwo3_1,textViewDetailsScreenOccupation,textViewPriceOne,textViewPriceTwo;
    private ProgressBar  progressBarDetailsScreen;
    private SeekBar seekBarPriceRange ;
    private ImageButton imageButtonDetailsScreenForward_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        //casting of textView
        textViewDetailsScreenGreet1=(TextView)findViewById(R.id.textViewDetailsScreenGreet1);
        textViewDetailsScreenTextTwo2_1=(TextView)findViewById(R.id.textViewDetailsScreenText2_1);
        getTextViewDetailsScreenTextTwo3_1=(TextView)findViewById(R.id.textViewDetailsScreenText3_1);
        textViewDetailsScreenOccupation=(TextView)findViewById(R.id.textViewDetailsScreenOccupation);
        textViewPriceOne=(TextView)findViewById(R.id.textViewPriceOne);
        textViewPriceTwo=(TextView)findViewById(R.id.textViewPriceTwo);

        //casting of ImageView
        imageViewDetailScreenHeader=(ImageView)findViewById(R.id.imageViewDetailsScreenHeader);

        //casting of progressbar
        progressBarDetailsScreen=(ProgressBar)findViewById(R.id.progressBarDetailsScreen);


        //casting of SeekBar
        seekBarPriceRange=(SeekBar)findViewById(R.id.seekBarPriceRange);

        //casting of iamgebutton
        imageButtonDetailsScreenForward_1=(ImageButton)findViewById(R.id.imageButtonDetailsScreenForward_1);

        imageButtonDetailsScreenForward_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Style.class));
            }
        });


    }
}
