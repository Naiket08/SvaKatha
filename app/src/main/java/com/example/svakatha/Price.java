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
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class Price extends AppCompatActivity {

    private ImageView imageViewPriceScreenHeader;
    private TextView textViewPriceScreenGreet,textViewPriceScreenText2,textViewPriceScreenText3,textViewPriceOne,textViewPriceTwo,textViewPriceScreenClothing;
    private ProgressBar progressBarPriceScreen;
    private SeekBar seekBarPriceRange_1;
    private ImageButton imageButtonPriceScreenForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        //casting of textView
        textViewPriceScreenGreet=(TextView)findViewById(R.id.textViewPriceScreenGreet1);
        textViewPriceScreenText2=(TextView)findViewById(R.id.textViewPriceScreenText2_1);
        textViewPriceScreenText3=(TextView)findViewById(R.id.textViewPriceScreenText3_1);
        textViewPriceScreenClothing=(TextView)findViewById(R.id.textViewPriceScreenClothing1);
        textViewPriceOne=(TextView)findViewById(R.id.textViewPriceOne);
        textViewPriceTwo=(TextView)findViewById(R.id.textViewPriceTwo);

        //casting of ImageView
        imageViewPriceScreenHeader=(ImageView)findViewById(R.id.imageViewPriceScreenHeader1);

        //casting of progressbar
        progressBarPriceScreen=(ProgressBar)findViewById(R.id.progressBarPriceScreen1);


        //casting of SeekBar
        seekBarPriceRange_1=(SeekBar)findViewById(R.id.seekBarPriceRange);

        //casting of iamgebutton
        imageButtonPriceScreenForward=(ImageButton)findViewById(R.id.imageButtonPriceScreenForward_1);

        imageButtonPriceScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), Style.class));

            }
        });


    }
}
