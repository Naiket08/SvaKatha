package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class Part_two extends AppCompatActivity {
    private ImageView imageViewUserScreenHeader;
    private TextView textViewUserScreenGreet,textViewUserScreenText2,textViewUserScreenText3,textViewFemale,textViewMale,textViewHeight,textViewWeight,textViewBirth,textViewCM,textViewKG;
    private ImageButton imageButtonUserScreenForward;
    private EditText editTextUserScreenHeight,editTextUserScreenWeight,editTextUserScreenBirth;
    private ProgressBar progressBarUserScreen;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_two);

        //casting of ImageView
        imageViewUserScreenHeader=(ImageView)findViewById(R.id.imageViewUserScreenHeader_1);
        //casting of TextView
        textViewUserScreenGreet=(TextView)findViewById(R.id.textViewUserScreenGreet_1);
        textViewUserScreenText2=(TextView)findViewById(R.id.textViewUserScreenText2_1);
        textViewUserScreenText3=(TextView)findViewById(R.id.textViewUserScreenText3_1);
        textViewFemale=(TextView)findViewById(R.id.textViewFemale_1);
        textViewMale=(TextView)findViewById(R.id.textViewMale_2);
        textViewHeight=(TextView)findViewById(R.id.textViewHeight_1);
        textViewWeight=(TextView)findViewById(R.id.textViewWeight_1);
        textViewBirth=(TextView)findViewById(R.id.textViewBirth_1);
        textViewCM=(TextView)findViewById(R.id.textViewCM_1);
        textViewKG=(TextView)findViewById(R.id.textViewKG_1);
        //casting of ImageButton
        imageButtonUserScreenForward=(ImageButton)findViewById(R.id.imageButtonUserScreenForward_1);
        //casting of EditText
        editTextUserScreenHeight=(EditText)findViewById(R.id.editTextUserScreenHeight_1);
        editTextUserScreenWeight=(EditText)findViewById(R.id.editTextUserScreenWeight_1);
        editTextUserScreenBirth=(EditText)findViewById(R.id.editTextUserScreenBirth_1);
        //casting of ProgressBar
        progressBarUserScreen=(ProgressBar)findViewById(R.id.progressBarUserScreen_1);
        //casting of Switch
        aSwitch=(Switch)findViewById(R.id.switch_1);

        imageButtonUserScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SkinTone.class));
            }
        });
    }
}
