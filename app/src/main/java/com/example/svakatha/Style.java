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

public class Style extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private TextView textViewStyleScreenGreet,textViewStyleScreenText2,textViewStyleScreenText3,textViewStyleScreenQuestion;
    private ImageView imageViewStyleScreenHeader;
    private ProgressBar progressBarStyleScreen;
    private EditText editTextStyleScreen;
    private ImageButton imageButtonStyleScreenForward;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        //casting of textview
        textViewStyleScreenGreet=(TextView)findViewById(R.id.textViewStyleScreenGreet1);
        textViewStyleScreenText2=(TextView)findViewById(R.id.textViewStyleScreenText2_1);
        textViewStyleScreenText3=(TextView)findViewById(R.id.textViewStyleScreenText3_1);
        textViewStyleScreenQuestion=(TextView)findViewById(R.id.textViewStyleScreenQuestion_1);
        Intent intent = getIntent();
        textViewStyleScreenGreet.setTypeface(textViewStyleScreenGreet.getTypeface(), Typeface.BOLD);
        final String name_style = intent.getStringExtra("Name_price");
        textViewStyleScreenGreet.setText("Hi"+" "+name_style);

        //casting of ImageView
        imageViewStyleScreenHeader=(ImageView)findViewById(R.id.imageViewStyleScreenHeader1);

        //casting of ProgressBar
        progressBarStyleScreen =(ProgressBar)findViewById(R.id.progressBarStyleScreen1);

        //casting of EditText
        editTextStyleScreen=(EditText)findViewById(R.id.editTextStyleScreen_3);

        //casting of ImageButton
        imageButtonStyleScreenForward=(ImageButton)findViewById(R.id.imageButtonStyleScreenForward_3);


        imageButtonStyleScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextStyleScreen.getText().toString().equals("")) {
                    Toast.makeText(Style.this, "Feild is empty", Toast.LENGTH_SHORT).show();
                } else {


                    Intent intent1 = new Intent(Style.this, Business.class);
                    intent1.putExtra("Name_style", name_style);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
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
            textViewStyleScreenGreet.setText("Hi "+account.getGivenName());
            /*userEmail.setText(account.getEmail());
            userId.setText(account.getId());
            try{
                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }*/

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
