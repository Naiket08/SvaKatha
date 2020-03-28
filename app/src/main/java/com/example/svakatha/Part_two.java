package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Part_two extends AppCompatActivity {
    private ImageView imageViewUserScreenHeader;
    private TextView textViewUserScreenGreet,textViewUserScreenText2,textViewUserScreenText3,textViewFemale,textViewMale,textViewHeight,textViewWeight,textViewBirth,textViewCM,textViewKG;
    private ImageButton imageButtonUserScreenForward;
    private EditText editTextUserScreenHeight,editTextUserScreenWeight,editTextUserScreenBirth;
    private ProgressBar progressBarUserScreen;
    private Switch aSwitch;
    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;

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
        textViewMale=(TextView)findViewById(R.id.textViewMale_1);
        textViewHeight=(TextView)findViewById(R.id.textViewHeight_1);
        textViewWeight=(TextView)findViewById(R.id.textViewWeight_1);
        textViewBirth=(TextView)findViewById(R.id.textViewBirth_1);
        textViewCM=(TextView)findViewById(R.id.textViewCM_1);
        textViewKG=(TextView)findViewById(R.id.textViewKG_1);
        aSwitch=(Switch)findViewById(R.id.switch1);
        //casting of ImageButton
        imageButtonUserScreenForward=(ImageButton)findViewById(R.id.imageButtonUserScreenForward_1);
        //casting of EditText
        editTextUserScreenHeight=(EditText)findViewById(R.id.editTextPartTwoHeight_1);
        editTextUserScreenWeight=(EditText)findViewById(R.id.editTextPartTwoWeight_1);
        editTextUserScreenBirth=(EditText)findViewById(R.id.editTextPartTwoBirth_1);

        Intent intent = getIntent();
        textViewUserScreenGreet.setTypeface(textViewUserScreenGreet.getTypeface(), Typeface.BOLD);
        String name_two = intent.getStringExtra("Name_business");
        textViewUserScreenGreet.setText("Hi"+" "+name_two);

        String[] size = new String[]{"Xs","S","M","L","XL","XXL","3XL","4XL"};
        Spinner spnr_size = findViewById(R.id.spnr_size);
        ArrayAdapter<String> sizearray =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        R.layout.dropdown_menu_popup_item,
                        size);

        spnr_size.setAdapter(sizearray);

        /*spnr_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                userId = auth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("users").document(userId);
                Map<String, Object> user = new HashMap<>();
                String Size;
                Size = spnr_size.getSelectedItem().toString();
                user.put("Size",Size);
                db.collection("users").document(userId).set(user, SetOptions.merge());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });*/

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarUserScreen_1);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 30,40);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();


        imageButtonUserScreenForward=(ImageButton)findViewById(R.id.imageButtonUserScreenForward_1);
        imageButtonUserScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = auth.getCurrentUser().getUid();
                    String male="MALE";
                    String female="FEMALE";
                    String Size = spnr_size.getSelectedItem().toString();
                    String Height=editTextUserScreenHeight.getText().toString();
                    String Weight=editTextUserScreenWeight.getText().toString();
                    String DOB=editTextUserScreenBirth.getText().toString();
                    Map<String, Object> user = new HashMap<>();
                    if(aSwitch.isChecked())
                    {
                        user.put("Gender",male);
                    }
                    else
                    {
                        user.put("Gender",female);

                    }
                    user.put("Height", Height);
                    user.put("Weight", Weight);
                    user.put("Birth", DOB);
                    user.put("Size",Size);
                    db.collection("users").document(userId).set(user, SetOptions.merge());

                Intent intent = new Intent(Part_two.this,SkinTone.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("Name_part_two", name_two);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }

        });

    }
}
