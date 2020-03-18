package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailsScreen extends AppCompatActivity {

    TextView textViewDetailsScreenGreet, textViewDetailsScreenText2, textViewDetailsScreenText3, textViewDetailsScreenOccupation;
    private ImageView imageViewDetailsScreenHeader;
    private EditText editTextDetailsScreenStyle;
    private ImageButton imageButtonDetailsScreenForward;
    private ProgressBar progressBarDetailsScreen;
    private FirebaseAuth auth;


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

        editTextDetailsScreenStyle = (EditText) findViewById(R.id.editTextDetailsScreenStyle);

        imageViewDetailsScreenHeader = (ImageView) findViewById(R.id.imageViewDetailsScreenHeader);

        progressBarDetailsScreen = (ProgressBar) findViewById(R.id.progressBarDetailsScreen);

        textViewDetailsScreenGreet = (TextView) findViewById(R.id.textViewDetailsScreenGreet);

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        imageButtonDetailsScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextDetailsScreenStyle.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent1 = new Intent(DetailsScreen.this, Price.class);
                    intent1.putExtra("Name_details", name_details);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                }
        });

        DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        String currentID = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewDetailsScreenGreet.setText("Hi "+finalProfileText);

                    }

                });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}