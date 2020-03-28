package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

public class DetailsScreen extends AppCompatActivity {

    TextView textViewDetailsScreenGreet, textViewDetailsScreenText2, textViewDetailsScreenText3, textViewDetailsScreenOccupation;
    private ImageView imageViewDetailsScreenHeader;
    private EditText editTextDetailsScreenStyle;
    private ImageButton imageButtonDetailsScreenForward;
    private ProgressBar progressBarDetailsScreen;
    private FirebaseAuth auth;
    private FirebaseFirestore mfirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);

        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);
        Intent intent = getIntent();
        textViewDetailsScreenGreet.setTypeface(textViewDetailsScreenGreet.getTypeface(),Typeface.BOLD);
        final String name_details = intent.getStringExtra("Name");
        textViewDetailsScreenGreet.setText("Hi"+" "+name_details);
        textViewDetailsScreenText2 = (TextView) findViewById(R.id.textViewDetailsScreenText2);
        textViewDetailsScreenText3 = (TextView) findViewById(R.id.textViewDetailsScreenText3);
        textViewDetailsScreenOccupation = (TextView) findViewById(R.id.textViewDetailsScreenOccupation);

        imageButtonDetailsScreenForward = (ImageButton) findViewById(R.id.imageButtonDetailsScreenForward);
        imageViewDetailsScreenHeader = (ImageView) findViewById(R.id.imageViewDetailsScreenHeader);

        String[] fashiondesigner = new String[]{"Fashion Designer","Architect/Engineering","Student","Art/Design",
                "Sales","Management","Community/SocialWork","Business/ClientServices","Retail","Legal",
                "Entertainer/Performer","Services","Entrepreneur","other"};
        Spinner spnr_occupation_detailsscreen = findViewById(R.id.spnr_occupation_detailsscreen);
        ArrayAdapter<String> fashion =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        R.layout.dropdown_menu_popup_item,
                        fashiondesigner);

        spnr_occupation_detailsscreen.setAdapter(fashion);

        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarDetailsScreen);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0,9);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();
        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);

        mfirestore = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        /*documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewDetailsScreenGreet.setText("Hi "+finalProfileText);
                        //FirebaseFirestore db = FirebaseFirestore.getInstance();
                        //String currentID = auth.getCurrentUser().getUid();
                        Toast.makeText(getApplicationContext(),""+currentID,Toast.LENGTH_SHORT).show();
                    }

                });*/


        imageButtonDetailsScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextDetailsScreenStyle.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    //DocumentReference documentReference = db.collection("users").document(currentID);
                    String occupation=editTextDetailsScreenStyle.getText().toString();
                    Map<String, Object> user = new HashMap<>();
                    user.put("Occupation", occupation);
                    db.collection("users").document(currentID).set(user, SetOptions.merge());
                    Intent intent = new Intent(DetailsScreen.this,Price.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("Name_details", name_details);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
                }
        });

        //DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        //databaseReference.setValue(occupation);

    }
}