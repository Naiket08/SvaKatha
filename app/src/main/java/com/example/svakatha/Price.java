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

import org.w3c.dom.Text;

public class Price extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ImageView imageViewPriceScreenHeader;
    private TextView textViewPriceScreenGreet,textViewPriceScreenText2,textViewPriceScreenText3,textViewPriceOne,textViewPriceTwo,textViewPriceScreenClothing;
    private ProgressBar progressBarPriceScreen;
    private SeekBar seekBarPriceRange_1;
    private ImageButton imageButtonPriceScreenForward;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

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
        Intent intent = getIntent();
        textViewPriceScreenGreet.setTypeface(textViewPriceScreenGreet.getTypeface(), Typeface.BOLD);
        final String name_price = intent.getStringExtra("Name_details");
        textViewPriceScreenGreet.setText("Hi"+" "+name_price);

        //casting of ImageView
        imageViewPriceScreenHeader=(ImageView)findViewById(R.id.imageViewPriceScreenHeader1);

        //casting of progressbar
        progressBarPriceScreen=(ProgressBar)findViewById(R.id.progressBarPriceScreen1);


        CrystalRangeSeekbar rangeseekbar = (CrystalRangeSeekbar)findViewById(R.id.rangeseekbar);

        rangeseekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                String min = String.valueOf(minValue);
                textViewPriceOne.setText(min);
                String max = String.valueOf(maxValue);
                textViewPriceTwo.setText(max);

            }
        });


        //casting of SeekBar
       
        //casting of iamgebutton
        imageButtonPriceScreenForward=(ImageButton)findViewById(R.id.imageButtonPriceScreenForward_1);

        imageButtonPriceScreenForward.setOnClickListener(new View.OnClickListener() {
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
            textViewPriceScreenGreet.setText(name);
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
