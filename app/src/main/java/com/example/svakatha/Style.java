package com.example.svakatha;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Style extends AppCompatActivity {

    private TextView textViewStyleScreenGreet,textViewStyleScreenText2,textViewStyleScreenText3,textViewStyleScreenQuestion;
    private ImageView imageViewStyleScreenHeader;
    private ProgressBar progressBarStyleScreen;
    private EditText editTextStyleScreen;
    private ImageButton imageButtonStyleScreenForward;
    String userId;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        //casting of textview
        textViewStyleScreenGreet=(TextView)findViewById(R.id.textViewStyleScreenGreet1);
        textViewStyleScreenText3=(TextView)findViewById(R.id.textViewStyleScreenText3_1);
        textViewStyleScreenQuestion=(TextView)findViewById(R.id.textViewStyleScreenQuestion_1);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        textViewStyleScreenGreet.setTypeface(textViewStyleScreenGreet.getTypeface(), Typeface.BOLD);
        final String name_style = intent.getStringExtra("Name_price");
        textViewStyleScreenGreet.setText("Hi"+" "+name_style);

        //casting of ImageView


        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarStyleScreen1);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 15,20);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();

        String[] style = new String[]{"Style","Comfort"};
        Spinner stylespinner = findViewById(R.id.spnr_whatyougofor);
        ArrayAdapter<String> stylearray =
                new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.dropdown_menu_popup_item,
                        style);
        stylespinner.setAdapter(stylearray);

        stylespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                userId = auth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("users").document(userId);
                Map<String, Object> user = new HashMap<>();
                String Style;
                Style = stylespinner.getSelectedItem().toString();
                user.put("Style",Style);
                db.collection("users").document(userId).set(user, SetOptions.merge());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //casting of ImageButton
        imageButtonStyleScreenForward=(ImageButton)findViewById(R.id.imageButtonStyleScreenForward_3);

        //Display Name of user
        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewStyleScreenGreet.setText("Hi "+finalProfileText);
                        textViewStyleScreenGreet.setTypeface(textViewStyleScreenGreet.getTypeface(),Typeface.BOLD);
                    }

                });


        imageButtonStyleScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent1 = new Intent(Style.this, Business.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //startActivity(intent1);
                    overridePendingTransition(0,0);
                    intent1.putExtra("Name_style", name_style);
                    startActivity(intent1);
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
        });

    }

}
