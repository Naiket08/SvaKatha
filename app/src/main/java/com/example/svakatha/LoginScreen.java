package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class LoginScreen extends AppCompatActivity {

    ImageView imageViewLoginHeader,imageViewLoginDivider;
    TextView textViewLoginWelcome,textViewLoginSvakatha,textViewLoginSignUp;
    EditText editTextLoginEmail,editTextLoginPassword;
    Button buttonLogin_login,googlelogin;
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    CallbackManager mcallbackManager;
    FirebaseAuth mfirebaseAuth;
    String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewLoginHeader = (ImageView)findViewById(R.id.imageViewLoginHeader);
        imageViewLoginDivider = (ImageView)findViewById(R.id.imageViewLoginDivider);
        textViewLoginWelcome = (TextView)findViewById(R.id.textViewLoginWelcome);
        textViewLoginSvakatha = (TextView)findViewById(R.id.textViewLoginSvakatha);
        textViewLoginSignUp = (TextView)findViewById(R.id.signup);
        editTextLoginEmail = (EditText)findViewById(R.id.editTextLoginEmailId);
        editTextLoginPassword = (EditText)findViewById(R.id.editTextLoginPassword);
        buttonLogin_login = (Button)findViewById(R.id.buttonLogin_login);

        mfirebaseAuth = FirebaseAuth.getInstance();


        buttonLogin_login.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String email2 = editTextLoginEmail.getText().toString().trim();
                                                     String password1 = editTextLoginPassword.getText().toString().trim();

                                                     if (TextUtils.isEmpty(email2)) {
                                                         editTextLoginEmail.setError("Email is required");
                                                         return;
                                                     }
                                                     if (TextUtils.isEmpty(password1)) {
                                                         editTextLoginPassword.setError("Password is required");
                                                         return;
                                                     }
                                                     if (password1.length() < 6) {
                                                         editTextLoginEmail.setError("Password Must be more than 6 character");
                                                         return;
                                                     }


                mfirebaseAuth.signInWithEmailAndPassword(email2,password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginScreen.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mfirebaseAuth.getCurrentUser();
                            updateUI(user);
                        }else{
                            editTextLoginEmail.setError("Invalid EmailID");
                            editTextLoginPassword.setError("Invalid Password");
                            Toast.makeText(LoginScreen.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
            }
        });

        textViewLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, SignupScreen.class);
                startActivity(intent);
                                                             //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }




        /*googlelogin=findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googlelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });*/



    public  void updateUI(FirebaseUser user)
    {
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            final String currentID = mfirebaseAuth.getCurrentUser().getUid();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = db.collection("users").document(currentID);
            documentReference.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String bodyshape = documentSnapshot.getString("BodyShape");
                            String name = documentSnapshot.getString("FirstName");
                            if(bodyshape=="")
                            {
                                Intent intent1 = new Intent(LoginScreen.this, DetailsScreen.class);
                                intent1.putExtra("Name", name);
                                startActivity(intent1);
                            }
                            else
                            {
                                Intent intent=new Intent(LoginScreen.this,ImageSelection.class);
                                intent.putExtra("Name_bodyshape_login", name);
                                startActivity(intent);
                            }
                            Toast.makeText(getApplicationContext(),""+currentID,Toast.LENGTH_SHORT).show();
                        }

                    });

            //overridePendingTransition(0,0);
        }
    }

}
    /*private void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }*/

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handlesigninResult(task);
        }
    }

    private  void handlesigninResult(Task<GoogleSignInAccount> completedtask)
    {
        try {
            GoogleSignInAccount account =completedtask.getResult(ApiException.class);
            Toast.makeText(this,"LogIn Successfull",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(account);
        }
        catch (ApiException e){
            Toast.makeText(this,"LogIn Failed",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LoginScreen.this, "Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mfirebaseAuth.getCurrentUser();
                    Intent intent=new Intent(LoginScreen.this,ImageSelection.class);
                    startActivity(intent);
                    //updateUI(user);
                }
                else
                {
                    Toast.makeText(LoginScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
    }*/



