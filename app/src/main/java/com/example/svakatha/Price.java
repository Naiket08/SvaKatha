package com.example.svakatha;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Price extends AppCompatActivity {

    private ImageView imageViewPriceScreenHeader;
    private TextView textViewPriceScreenGreet,textViewPriceScreenText2,textViewPriceScreenText3,textViewPriceOne,textViewPriceTwo,textViewPriceScreenClothing;
    private ProgressBar progressBarPriceScreen;
    private SeekBar seekBarPriceRange_1;
    //private CrystalRangeSeekBar;
    private ImageButton imageButtonPriceScreenForward;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        //casting of textView
        textViewPriceScreenGreet=(TextView)findViewById(R.id.textViewPriceScreenGreet1);
        textViewPriceScreenText3=(TextView)findViewById(R.id.textViewPriceScreenText3_1);
        textViewPriceScreenClothing=(TextView)findViewById(R.id.textViewPriceScreenClothing1);
        textViewPriceOne=(TextView)findViewById(R.id.textViewPriceOne);
        textViewPriceTwo=(TextView)findViewById(R.id.textViewPriceTwo);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        textViewPriceScreenGreet.setTypeface(textViewPriceScreenGreet.getTypeface(), Typeface.BOLD);
        final String name_price = intent.getStringExtra("Name_details");
        textViewPriceScreenGreet.setText("Hi"+" "+name_price);

        //casting of ImageView
        imageViewPriceScreenHeader=(ImageView)findViewById(R.id.imageViewPriceScreenHeader1);

        CrystalRangeSeekbar rangeseekbar = (CrystalRangeSeekbar)findViewById(R.id.rangeseekbar);

        //casting of progressbar

        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarPriceScreen1);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 9,15);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();



        rangeseekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                String min = String.valueOf(minValue);
                textViewPriceOne.setText("\u20B9"+" "+min);
                String max = String.valueOf(maxValue);
                textViewPriceTwo.setText("\u20B9"+" "+max);

            }
        });

        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        /*documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewPriceScreenGreet.setText("Hi "+finalProfileText);
                        textViewPriceScreenGreet.setTypeface(textViewPriceScreenGreet.getTypeface(),Typeface.BOLD);
                    }

                });*/



        //casting of SeekBar
       
        //casting of iamgebutton
        imageButtonPriceScreenForward=(ImageButton)findViewById(R.id.imageButtonPriceScreenForward_1);

        imageButtonPriceScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Price=textViewPriceTwo.getText().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("Price", Price);
                db.collection("users").document(currentID).set(user, SetOptions.merge());

                Intent intent1 = new Intent(Price.this,Style.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //startActivity(intent1);
                overridePendingTransition(0,0);
                intent1.putExtra("Name_price",name_price);
                startActivity(intent1);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });




    }

}
