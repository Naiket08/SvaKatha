package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputLayout;

public class SignupScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    ImageView imageViewSignUpHeader, imageViewSignUpDivider;
    TextView textViewSignUpWelcome, textViewSignUpSvakatha;
    Button buttonSignUp_LetsStart;
    EditText editTextSignUpUsrname,editTextSignUpEmailId,editTextSignUpPassword,editTextSignUpConfirmPassword;
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewSignUpHeader = (ImageView)findViewById(R.id.imageViewSignUpHeader);
        imageViewSignUpDivider = (ImageView)findViewById(R.id.imageViewSignUpDivider);
        textViewSignUpWelcome = (TextView)findViewById(R.id.textViewSignUpWelcome);
        textViewSignUpSvakatha = (TextView)findViewById(R.id.textViewSignUpSvakatha);
        buttonSignUp_LetsStart = (Button)findViewById(R.id.buttonSignUp_LetsStart);
        editTextSignUpUsrname = (EditText)findViewById(R.id.editTextSignUpUsername);
        editTextSignUpEmailId = (EditText)findViewById(R.id.editTextSignUpEmailId);
        editTextSignUpPassword = (EditText)findViewById(R.id.editTextSignUpPassword);
        editTextSignUpConfirmPassword = (EditText)findViewById(R.id.editTextSignUpConfirmPassword);

        buttonSignUp_LetsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSignUpUsrname.getText().toString().isEmpty()||editTextSignUpEmailId.getText().toString().isEmpty()||editTextSignUpPassword.getText().toString().isEmpty()||editTextSignUpConfirmPassword.getText().toString().isEmpty()){
                    if(editTextSignUpUsrname.getText().toString().isEmpty()){
                        editTextSignUpUsrname.setError("Please enter Username");
                    }
                    if(editTextSignUpEmailId.getText().toString().isEmpty()){
                        editTextSignUpEmailId.setError("Please enter Email ID");
                    }
                    if(editTextSignUpPassword.getText().toString().isEmpty()){
                        editTextSignUpPassword.setError("Please enter Password");
                    }
                    if(editTextSignUpConfirmPassword.getText().toString().isEmpty()){
                        editTextSignUpConfirmPassword.setError("Please enter Confirm Password");
                    }
                }
                else if(editTextSignUpUsrname.getText().toString().isEmpty()&&editTextSignUpEmailId.getText().toString().isEmpty()&&editTextSignUpPassword.getText().toString().isEmpty()&&editTextSignUpConfirmPassword.getText().toString().isEmpty()){
                    editTextSignUpUsrname.setError("Please enter Username");
                    editTextSignUpEmailId.setError("Please enter Email ID");
                    editTextSignUpPassword.setError("Please enter Password");
                    editTextSignUpConfirmPassword.setError("Please enter Confirm Password");
                    Toast.makeText(SignupScreen.this, "All Fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(editTextSignUpPassword.getText().toString().equals(editTextSignUpConfirmPassword.getText().toString())){
                        String name = editTextSignUpUsrname.getText().toString();
                        Intent intent = new Intent(SignupScreen.this,DetailsScreen.class);
                        intent.putExtra("Name",name);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    else{
                        editTextSignUpConfirmPassword.setError("Password does not match");
                    }
                }
            }
        });

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){
        Intent intent=new Intent(SignupScreen.this,DetailsScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextSignUpUsrname.setText("");
        editTextSignUpEmailId.setText("");
        editTextSignUpPassword.setText("");
        editTextSignUpConfirmPassword.setText("");
        editTextSignUpUsrname.requestFocus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
