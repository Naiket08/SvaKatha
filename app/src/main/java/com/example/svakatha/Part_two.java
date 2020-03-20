package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
        //casting of ImageButton
        imageButtonUserScreenForward=(ImageButton)findViewById(R.id.imageButtonUserScreenForward_1);
        //casting of EditText
        editTextUserScreenHeight=(EditText)findViewById(R.id.editTextPartTwoHeight_1);
        editTextUserScreenWeight=(EditText)findViewById(R.id.editTextPartTwoWeight_1);
        editTextUserScreenBirth=(EditText)findViewById(R.id.editTextPartTwoBirth_1);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarUserScreen_1);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 30,40);
        progressAnimator.setDuration(900);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
        //casting of Switch
        aSwitch=(Switch)findViewById(R.id.switch1);

        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewUserScreenGreet.setText("Hi "+finalProfileText);

                    }

                });


        imageButtonUserScreenForward=(ImageButton)findViewById(R.id.imageButtonUserScreenForward_1);
        imageButtonUserScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextUserScreenHeight.getText().toString().equals("") || editTextUserScreenWeight.getText().toString().equals("") || editTextUserScreenBirth.getText().toString().equals("")) {
                    Toast.makeText(Part_two.this, "Feilds Are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    String Height=editTextUserScreenHeight.getText().toString();
                    String Weight=editTextUserScreenWeight.getText().toString();
                    String DOB=editTextUserScreenBirth.getText().toString();
                    Map<String, Object> user = new HashMap<>();
                    user.put("Height", Height);
                    user.put("Weight", Weight);
                    user.put("Birth", DOB);
                    db.collection("users").document(currentID).set(user, SetOptions.merge());

                    startActivity(new Intent(getApplicationContext(), SkinTone.class));
                }
            }
        });

    }
}
