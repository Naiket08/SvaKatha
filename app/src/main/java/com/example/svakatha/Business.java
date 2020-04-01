package com.example.svakatha;

import androidx.annotation.NonNull;
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

public class Business extends AppCompatActivity {

    private ImageView imageViewBusinessScreenHeader;
    private TextView textViewBusinessScreenGreet,textViewBusinessScreenText2,textViewBusinessScreenText3,textViewBusinessScreenOccasion;
    private EditText editTextBusinessScreen;
    private ImageButton imageButtonBusinessScreenForward;
    private ProgressBar progressBarBusinessScreen;
    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        //casting of TextView

        textViewBusinessScreenText2=(TextView)findViewById(R.id.textViewBusinessScreenText2_1);
        textViewBusinessScreenText3=(TextView)findViewById(R.id.textViewBusinessScreenText3_1);
        textViewBusinessScreenOccasion=(TextView)findViewById(R.id.textViewBusinessScreenOccasion_1);
        textViewBusinessScreenGreet=(TextView)findViewById(R.id.textViewBusinessScreenGreet1);
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        textViewBusinessScreenGreet.setTypeface(textViewBusinessScreenGreet.getTypeface(), Typeface.BOLD);
        String name_business = intent.getStringExtra("Name_style");
        textViewBusinessScreenGreet.setText("Hi"+" "+name_business);

        //casting of ImageView
        imageViewBusinessScreenHeader=(ImageView)findViewById(R.id.imageViewBusinessScreenHeader1);

        String[] business = new String[]{"Ocassion","Business","Dinner party","A business dinner or a company party",
                "Family Get-Together or Birthday Party","College","Cocktail party","Business Formal","Religious Ceremony",
                "Interview","A Night at the Theater","Office","Other"};
        Spinner spnr_business = findViewById(R.id.spnr_occasion_bussiness);
        ArrayAdapter<String> businessarray =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        R.layout.dropdown_menu_popup_item,
                        business);

        spnr_business.setAdapter(businessarray);

        spnr_business.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                userId = auth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("users").document(userId);
                Map<String, Object> user = new HashMap<>();
                String Bussiness;
                Bussiness = spnr_business.getSelectedItem().toString();
                user.put("Business",Bussiness);
                db.collection("users").document(userId).set(user, SetOptions.merge());
                String X=String.valueOf(0);
                user.put("AddCart",X);
                db.collection("users").document(userId).set(user, SetOptions.merge());
                String Y=String.valueOf(0);
                user.put("Counter",Y);
                db.collection("users").document(userId).set(user, SetOptions.merge());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //casting of ImageButton
        imageButtonBusinessScreenForward=(ImageButton)findViewById(R.id.imageButtonBusinessScreenForward_1);


        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarBusinessScreen1);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 20,30);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();


        imageButtonBusinessScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Business.this,Part_two.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("Name_business", name_business);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
        });
    }


}
