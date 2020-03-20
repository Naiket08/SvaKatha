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
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Style extends AppCompatActivity {

    private TextView textViewStyleScreenGreet,textViewStyleScreenText2,textViewStyleScreenText3,textViewStyleScreenQuestion;
    private ImageView imageViewStyleScreenHeader;
    private ProgressBar progressBarStyleScreen;
    private EditText editTextStyleScreen;
    private ImageButton imageButtonStyleScreenForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        //casting of textview
        textViewStyleScreenGreet=(TextView)findViewById(R.id.textViewStyleScreenGreet1);
        textViewStyleScreenText2=(TextView)findViewById(R.id.textViewStyleScreenText2_1);
        textViewStyleScreenText3=(TextView)findViewById(R.id.textViewStyleScreenText3_1);
        textViewStyleScreenQuestion=(TextView)findViewById(R.id.textViewStyleScreenQuestion_1);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        textViewStyleScreenGreet.setTypeface(textViewStyleScreenGreet.getTypeface(), Typeface.BOLD);
        //final String name_style = intent.getStringExtra("Name_price");
        //textViewStyleScreenGreet.setText("Hi"+" "+name_style);

        //casting of ImageView
        imageViewStyleScreenHeader=(ImageView)findViewById(R.id.imageViewStyleScreenHeader1);

        //casting of ProgressBar
        progressBarStyleScreen =(ProgressBar)findViewById(R.id.progressBarStyleScreen1);

        //casting of EditText
        editTextStyleScreen=(EditText)findViewById(R.id.editTextStyleScreen_3);

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
                        textViewStyleScreenGreet.setText(finalProfileText);

                    }

                });


        imageButtonStyleScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextStyleScreen.getText().toString().equals("")) {
                    Toast.makeText(Style.this, "Feild is empty", Toast.LENGTH_SHORT).show();
                } else {

                    String Style=editTextStyleScreen.getText().toString();
                    Map<String, Object> user = new HashMap<>();
                    user.put("Style", Style);
                    db.collection("users").document(currentID).set(user, SetOptions.merge());
                    Intent intent1 = new Intent(Style.this, Business.class);
                    //intent1.putExtra("Name_style", name_style);
                    startActivity(intent1);
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                }
        });

    }

}
