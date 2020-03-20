package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
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

import org.w3c.dom.Text;

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
        textViewPriceScreenText2=(TextView)findViewById(R.id.textViewPriceScreenText2_1);
        textViewPriceScreenText3=(TextView)findViewById(R.id.textViewPriceScreenText3_1);
        textViewPriceScreenClothing=(TextView)findViewById(R.id.textViewPriceScreenClothing1);
        textViewPriceOne=(TextView)findViewById(R.id.textViewPriceOne);
        textViewPriceTwo=(TextView)findViewById(R.id.textViewPriceTwo);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        /*Intent intent = getIntent();
        textViewPriceScreenGreet.setTypeface(textViewPriceScreenGreet.getTypeface(), Typeface.BOLD);
        final String name_price = intent.getStringExtra("Name_details");
        textViewPriceScreenGreet.setText("Hi"+" "+name_price);*/

        //casting of ImageView
        imageViewPriceScreenHeader=(ImageView)findViewById(R.id.imageViewPriceScreenHeader1);

        CrystalRangeSeekbar rangeseekbar = (CrystalRangeSeekbar)findViewById(R.id.rangeseekbar);

        //casting of progressbar
        progressBarPriceScreen=(ProgressBar)findViewById(R.id.progressBarPriceScreen1);


        rangeseekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                String min = String.valueOf(minValue);
                textViewPriceOne.setText(min);
                String max = String.valueOf(maxValue);
                textViewPriceTwo.setText(max);

            }
        });

        final String currentID = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewPriceScreenGreet.setText("Hi "+finalProfileText);

                    }

                });



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
                //intent1.putExtra("Name_price",name_price);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });


    }

}
