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

public class Business extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ImageView imageViewBusinessScreenHeader;
    private TextView textViewBusinessScreenGreet,textViewBusinessScreenText2,textViewBusinessScreenText3,textViewBusinessScreenOccasion;
    private EditText editTextBusinessScreen;
    private ImageButton imageButtonBusinessScreenForward;
    private ProgressBar progressBarBusinessScreen;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        //casting of TextView

        textViewBusinessScreenText2=(TextView)findViewById(R.id.textViewBusinessScreenText2_1);
        textViewBusinessScreenText3=(TextView)findViewById(R.id.textViewBusinessScreenText3_1);
        textViewBusinessScreenOccasion=(TextView)findViewById(R.id.textViewBusinessScreenOccasion_1);
        textViewBusinessScreenGreet=(TextView)findViewById(R.id.textViewBusinessScreenGreet1);
        Intent intent = getIntent();
        textViewBusinessScreenGreet.setTypeface(textViewBusinessScreenGreet.getTypeface(), Typeface.BOLD);
        String name_business = intent.getStringExtra("Name_style");
        textViewBusinessScreenGreet.setText("Hi"+" "+name_business);

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

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



    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            String name = "Hi "+account.getGivenName();
            textViewBusinessScreenGreet.setText(name);
            /*userEmail.setText(account.getEmail());
            userId.setText(account.getId());
            try{
                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }*/

        }else{
            //gotoMainActivity();
        }
    }
    private void gotoMainActivity(){
        Intent intent=new Intent(this,SignupScreen.class);
        startActivity(intent);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }
}
