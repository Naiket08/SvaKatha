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

    private TextView textViewStyleScreenGreet,textViewStyleScreenText2,textViewStyleScreenText3,textViewStyleScreenQuestion;
    private ImageView imageViewStyleScreenHeader;
    private ProgressBar progressBarStyleScreen;
    private EditText editTextStyleScreen;
    private ImageButton imageButtonStyleScreenForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        //casting of textview
        textViewStyleScreenGreet=(TextView)findViewById(R.id.textViewStyleScreenGreet1);
        textViewStyleScreenText2=(TextView)findViewById(R.id.textViewStyleScreenText2_1);
        textViewStyleScreenText3=(TextView)findViewById(R.id.textViewStyleScreenText3_1);
        textViewStyleScreenQuestion=(TextView)findViewById(R.id.textViewStyleScreenQuestion_1);

        //casting of ImageView
        imageViewStyleScreenHeader=(ImageView)findViewById(R.id.imageViewStyleScreenHeader1);

        //casting of ProgressBar
        progressBarStyleScreen =(ProgressBar)findViewById(R.id.progressBarStyleScreen1);

        //casting of EditText
        editTextStyleScreen=(EditText)findViewById(R.id.editTextStyleScreen_3);

        //casting of ImageButton
        imageButtonStyleScreenForward=(ImageButton)findViewById(R.id.imageButtonStyleScreenForward_3);


        imageButtonStyleScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Business.class));
            }
        });
    }
}
