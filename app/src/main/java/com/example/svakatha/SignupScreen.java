package com.example.svakatha;

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

import com.google.android.material.textfield.TextInputLayout;

public class SignupScreen extends AppCompatActivity {

    ImageView imageViewSignUpHeader, imageViewSignUpDivider;
    TextView textViewSignUpWelcome, textViewSignUpSvakatha;
    Button buttonSignUp_LetsStart;
    EditText editTextSignUpUsrname,editTextSignUpEmailId,editTextSignUpPassword,editTextSignUpConfirmPassword;

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
