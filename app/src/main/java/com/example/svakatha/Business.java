package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Business extends AppCompatActivity {

    private ImageView imageViewBusinessScreenHeader;
    private TextView textViewBusinessScreenGreet,textViewBusinessScreenText2,textViewBusinessScreenText3,textViewBusinessScreenOccasion;
    private EditText editTextBusinessScreen;
    private ImageButton imageButtonBusinessScreenForward;
    private ProgressBar progressBarBusinessScreen;
    private FirebaseAuth auth;


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
        //String name_business = intent.getStringExtra("Name_style");
        //textViewBusinessScreenGreet.setText("Hi"+" "+name_business);


        //casting of ImageView
        imageViewBusinessScreenHeader=(ImageView)findViewById(R.id.imageViewBusinessScreenHeader1);

        //casting of EditText
        editTextBusinessScreen=(EditText)findViewById(R.id.editTextBusinessScreen_1);

        //casting of ImageButton
        imageButtonBusinessScreenForward=(ImageButton)findViewById(R.id.imageButtonBusinessScreenForward_1);

        //casting of ProgressBar
        progressBarBusinessScreen =(ProgressBar)findViewById(R.id.progressBarBusinessScreen1);
        
        imageButtonBusinessScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextBusinessScreen.getText().toString().equals("")) {
                    Toast.makeText(Business.this, "Field is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), Part_two.class));
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
                        textViewBusinessScreenGreet.setText("Hi "+finalProfileText);

                    }

                });

    }


}
