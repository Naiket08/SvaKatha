package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.w3c.dom.Text;

public class Price extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ImageView imageViewDetailScreenHeader;
    private TextView textViewDetailsScreenGreet1,textViewDetailsScreenTextTwo2_1,getTextViewDetailsScreenTextTwo3_1,textViewDetailsScreenOccupation,textViewPriceOne,textViewPriceTwo;
    private ProgressBar  progressBarDetailsScreen;
    private SeekBar seekBarPriceRange ;
    private ImageButton imageButtonDetailsScreenForward_1;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        //casting of textView
        textViewDetailsScreenGreet1=(TextView)findViewById(R.id.textViewDetailsScreenGreet1);
        Intent intent = getIntent();
        textViewDetailsScreenGreet1.setTypeface(textViewDetailsScreenGreet1.getTypeface(), Typeface.BOLD);
        final String name_price = intent.getStringExtra("Name_details");
        textViewDetailsScreenGreet1.setText("Hi"+" "+name_price);
        textViewDetailsScreenTextTwo2_1=(TextView)findViewById(R.id.textViewDetailsScreenText2_1);
        getTextViewDetailsScreenTextTwo3_1=(TextView)findViewById(R.id.textViewDetailsScreenText3_1);
        textViewDetailsScreenOccupation=(TextView)findViewById(R.id.textViewDetailsScreenOccupation);
        textViewPriceOne=(TextView)findViewById(R.id.textViewPriceOne);
        textViewPriceTwo=(TextView)findViewById(R.id.textViewPriceTwo);

        //casting of ImageView
        imageViewDetailScreenHeader=(ImageView)findViewById(R.id.imageViewDetailsScreenHeader);

        //casting of progressbar
        progressBarDetailsScreen=(ProgressBar)findViewById(R.id.progressBarDetailsScreen);


        //casting of SeekBar
        seekBarPriceRange=(SeekBar)findViewById(R.id.seekBarPriceRange);

        //casting of iamgebutton
        imageButtonDetailsScreenForward_1=(ImageButton)findViewById(R.id.imageButtonDetailsScreenForward_1);

        imageButtonDetailsScreenForward_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Price.this,Style.class);
                intent1.putExtra("Name_price",name_price);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
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
            textViewDetailsScreenGreet1.setText(name);
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
