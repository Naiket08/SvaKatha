package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupScreen extends AppCompatActivity {

    private static final String TAG = "Status";
    ImageView imageViewSignUpHeader, imageViewSignUpDivider;
    TextView textViewSignUpWelcome, textViewSignUpSvakatha;
    Button buttonSignUp_LetsStart;
    EditText editTextSignUpUsrname,editTextSignUpEmailId,editTextSignUpPassword,editTextSignUpConfirmPassword;
    SignInButton signInButton;
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    CallbackManager mcallbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    LoginButton facebook_login_button;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mfirebaseAuth;
    String userId;


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
        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;

        mfirebaseAuth =FirebaseAuth.getInstance();

        signInButton=findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        buttonSignUp_LetsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        /*buttonSignUp_LetsStart.setOnClickListener(new View.OnClickListener() {
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
        });*/



        /*if (!loggedOut) {
            Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());

            //Using Graph API
            getUserProfile(AccessToken.getCurrentAccessToken());
        }*/

       mcallbackManager = CallbackManager.Factory.create();
        facebook_login_button = (LoginButton) findViewById(R.id.facebook_login_button);
        facebook_login_button.setPermissions("public_profile","email", "user_birthday");
        facebook_login_button.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.i(TAG, "onSuccess: logged in successfully");
                Toast.makeText(SignupScreen.this, "Login Successful", Toast.LENGTH_SHORT).show();

                handleFacebookAccessToken(loginResult.getAccessToken());
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        Log.i(TAG, "onCompleted: response: " + response.toString());
                        Toast.makeText(SignupScreen.this, "Facebook1", Toast.LENGTH_SHORT).show();
                        try {
                            String email = object.getString("email");
                            String birthday = object.getString("birthday");
                            Toast.makeText(getApplicationContext(),""+email,Toast.LENGTH_LONG).show();
                            Toast.makeText(SignupScreen.this, "Facebook2", Toast.LENGTH_SHORT).show();


                            Log.i(TAG, "onCompleted: Email: " + email);
                            Log.i(TAG, "onCompleted: Birthday: " + birthday);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, "onCompleted: JSON exception");
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
//        facebook_login_button.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
//                Log.d("API123", loggedIn + " ??");
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
//        if (currentUser != null) {
//            Log.i(TAG, "onStart: Someone logged in <3");
//            Toast.makeText(SignupScreen.this, "Facebook4", Toast.LENGTH_SHORT).show();
//
//        } else {
//            Log.i(TAG, "onStart: No one logged in :/");
//        }
//
//    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        final FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            //Toast.makeText(SignupScreen.this, "Facebook5", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Log.i(TAG, "onComplete: login completed with user: " + user.getDisplayName());
                          //  startActivity(new Intent(getApplicationContext(), DetailsScreen.class));
                            getUserProfile(AccessToken.getCurrentAccessToken());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignupScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            userId = mfirebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = db.collection("users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            String first_name = object.getString("first_name");
                            user.put("FirstName", first_name);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignupScreen.this, "Your Details are entered in Database", Toast.LENGTH_SHORT).show();
                                }
                            });
                            //String email = object.getString("email");
                            //Toast.makeText(getApplicationContext(),""+first_name,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), DetailsScreen.class));
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void registerUser(){
        //getting email and password from edit texts
        final String email =editTextSignUpEmailId.getText().toString().trim();
        final String password  = editTextSignUpPassword.getText().toString().trim();
        final String confirmpassword=editTextSignUpConfirmPassword.getText().toString().trim();
        final String username=editTextSignUpUsrname.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter First Name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(confirmpassword)){
            Toast.makeText(this,"Please enter confirm password",Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(confirmpassword)){
            Toast.makeText(this, "Please Match The Password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog


        //creating a new user
        mfirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignupScreen.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            //Database connection

                            userId = mfirebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = db.collection("users").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Email",email);
                            user.put("Password",password);
                            user.put("FirstName",username);
                            user.put("Business","");
                            user.put("Style","");
                            user.put("Price","");
                            user.put("Occupation","");
                            //user.put("closetChoiceDocName","");
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignupScreen.this, "Your Details are entered in Database", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),DetailsScreen.class));
                        }else{
                            //display some message here
                            Toast.makeText(SignupScreen.this,"Already Registered", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


    /*@Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            gotoProfile(account);
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile(GoogleSignInAccount acc){

        String email=acc.getEmail();
        String username=acc.getGivenName();
        userId = mfirebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("users").document(userId);
        Map<String,Object> user = new HashMap<>();
        user.put("Email",email);
        //user.put("Password",password);
        user.put("FirstName",username);
        user.put("Business","");
        user.put("Style","");
        user.put("Price","");
        user.put("Occupation","");
        //user.put("closetChoiceDocName","");

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SignupScreen.this, "Your Details are entered in Database", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent=new Intent(SignupScreen.this,DetailsScreen.class);
        startActivity(intent);
    }*/

    private void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount>task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handlesigninResult(task);
        }
    }

    private  void handlesigninResult(Task<GoogleSignInAccount> completedtask)
    {
        try {
            GoogleSignInAccount account =completedtask.getResult(ApiException.class);
            Toast.makeText(this,"Sign In Successfull",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(account);
        }
        catch (ApiException e){
            Toast.makeText(this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);

        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc)
    {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(),null);
        mfirebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignupScreen.this, "Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mfirebaseAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    Toast.makeText(SignupScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        editTextSignUpUsrname.setText("");
        editTextSignUpEmailId.setText("");
        editTextSignUpPassword.setText("");
        editTextSignUpConfirmPassword.setText("");
        editTextSignUpUsrname.requestFocus();
    }*/

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }*/

    private void updateUI(FirebaseUser user1)
    {
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acc!=null)
        {
            String email=acc.getEmail();
            String username=acc.getGivenName();
            userId = mfirebaseAuth.getCurrentUser().getUid();
            DocumentReference documentReference = db.collection("users").document(userId);
            Map<String,Object> user = new HashMap<>();
            user.put("Email",email);
            //user.put("Password",password);
            user.put("FirstName",username);
            user.put("Business","");
            user.put("Style","");
            user.put("Price","");
            user.put("Occupation","");
            //user.put("closetChoiceDocName","");

            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(SignupScreen.this, "Your Details are entered in Database", Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent=new Intent(SignupScreen.this,DetailsScreen.class);
            startActivity(intent);
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            startActivity(new Intent(SignupScreen.this, DetailsScreen.class));
        }
    }
}
