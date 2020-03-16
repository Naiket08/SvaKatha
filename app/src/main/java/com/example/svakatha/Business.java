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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

public class Business extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ImageView imageViewDetailsScreenHeader;
    private TextView textViewDetailsScreenGreet3,textViewDetailsScreenText2,textViewDetailsScreenText3,textViewDetailsScreenOccupation;
    private EditText editTextDetailsScreenStyle_4;
    private ImageButton imageButtonDetailsScreenForward_4;
    private ProgressBar progressBarDetailsScreen;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        //casting of TextView
        textViewDetailsScreenGreet3=(TextView)findViewById(R.id.textViewBusinessGreet);
        Intent intent = getIntent();
        textViewDetailsScreenGreet3.setTypeface(textViewDetailsScreenGreet3.getTypeface(), Typeface.BOLD);
        String name_business = intent.getStringExtra("Name_style");
        textViewDetailsScreenGreet3.setText("Hi"+" "+name_business);
        textViewDetailsScreenText2=(TextView)findViewById(R.id.textViewDetailsScreenText2);
        textViewDetailsScreenText3=(TextView)findViewById(R.id.textViewDetailsScreenText3);
        textViewDetailsScreenOccupation=(TextView)findViewById(R.id.textViewDetailsScreenOccupation);

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        //casting of ImageView
        imageViewDetailsScreenHeader=(ImageView)findViewById(R.id.imageViewDetailsScreenHeader);

        //casting of EditText
        editTextDetailsScreenStyle_4=(EditText)findViewById(R.id.editTextDetailsScreenStyle_4);

        //casting of ImageButton
        imageButtonDetailsScreenForward_4=(ImageButton)findViewById(R.id.imageButtonDetailsScreenForward_4);

        //casting of ProgressBar
        progressBarDetailsScreen =(ProgressBar)findViewById(R.id.progressBarDetailsScreen);



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
            textViewDetailsScreenGreet3.setText(name);
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
