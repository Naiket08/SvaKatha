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

import org.w3c.dom.Text;

public class LoginScreen extends AppCompatActivity {

    ImageView imageViewLoginHeader,imageViewLoginDivider;
    TextView textViewLoginWelcome,textViewLoginSvakatha,textViewLoginSignUp;
    EditText editTextLoginEmail,editTextLoginPassword;
    Button buttonLogin_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewLoginHeader = (ImageView)findViewById(R.id.imageViewLoginHeader);
        imageViewLoginDivider = (ImageView)findViewById(R.id.imageViewLoginDivider);
        textViewLoginWelcome = (TextView)findViewById(R.id.textViewLoginWelcome);
        textViewLoginSvakatha = (TextView)findViewById(R.id.textViewLoginSvakatha);
        textViewLoginSignUp = (TextView)findViewById(R.id.textViewLoginSignUp);
        editTextLoginEmail = (EditText)findViewById(R.id.editTextLoginEmailId);
        editTextLoginPassword = (EditText)findViewById(R.id.editTextLoginPassword);
        buttonLogin_login = (Button)findViewById(R.id.buttonLogin_login);

        buttonLogin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextLoginEmail.getText().toString().isEmpty()||editTextLoginPassword.getText().toString().isEmpty()){
                    if(editTextLoginEmail.getText().toString().isEmpty()){
                        editTextLoginEmail.setError("Please enter Email ID");
                    }
                    else{
                        editTextLoginPassword.setError("Please enter Password");
                    }
                }
                else{
                    if(editTextLoginEmail.getText().toString().equals("abc@abc.com")){
                        if(editTextLoginPassword.getText().toString().equals("19191919")){
                            Toast.makeText(LoginScreen.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            editTextLoginPassword.setError("Wrong Password");
                        }
                    }
                    else{
                        editTextLoginEmail.setError("Wrong EmailID");
                    }
                }
            }
        });

        textViewLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,SignupScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
}
