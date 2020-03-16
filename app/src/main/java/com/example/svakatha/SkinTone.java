package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SkinTone extends AppCompatActivity {

    private ImageView imageViewSkinToneScreenToneHeader;
    private TextView textViewSkinToneScreenGreet,textViewSkinToneScreenText2,textViewSkinToneScreenText3,textViewSkinTone;
    private ImageButton imageButtonSkinToneScreenForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_tone);
        //casting of imageview
        imageViewSkinToneScreenToneHeader=(ImageView)findViewById(R.id.imageViewSkinToneScreenHeader_1);
        //casting of textView
        textViewSkinToneScreenGreet=(TextView)findViewById(R.id.textViewSkinToneScreenGreet_1);
        textViewSkinToneScreenText2=(TextView)findViewById(R.id.textViewUserScreenText2_1);
        textViewSkinToneScreenText3=(TextView)findViewById(R.id.textViewSkinToneScreenText3_1);
        textViewSkinTone=(TextView)findViewById(R.id.skintone);
        //casting of imagebutton
        imageButtonSkinToneScreenForward=(ImageButton)findViewById(R.id.imageButtonSkinToneScreenForward_1);

        imageButtonSkinToneScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BodyShape.class));
            }
        });
    }
}
