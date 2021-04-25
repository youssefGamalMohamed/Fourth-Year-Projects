package com.example.souq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.souq.Classes.Category;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;

    private EditText email , password;
    private TextView status;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null)
        {
            Intent intent = new Intent(getApplicationContext() , CategoriesActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.login_email) ;
        password = findViewById(R.id.login_password);
        status = findViewById(R.id.error_or_successfully_login);

        mAuth = FirebaseAuth.getInstance();

        createRequest();
    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this,e.getMessage() , Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext() , CategoriesActivity.class);
                            startActivity(intent);
                            
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this , "Sorry Authentication Failed" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void LogIn(View view) {
        String email = this.email.getText().toString() , password = this.password.getText().toString();

        if(email.equals("") || password.equals("")){
            if(email.equals("")){
                giveTextAndColorToTextView((View)status , "Missing Email" , R.color.dark_red);
            }
            if(password.equals("")){

                giveTextAndColorToTextView((View)status , "Missing Password" , R.color.dark_red);
            }
            if(email.equals("") && password.equals("")){

                giveTextAndColorToTextView((View)status , "Missing Email and Password" , R.color.dark_red);
            }

        }
        else{
            mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        giveTextAndColorToTextView((View)status , "Successfully Log In" , R.color.dark_green);
                        startActivity(new Intent(MainActivity.this , CategoriesActivity.class));
                    }else{
                        giveTextAndColorToTextView((View)status , "Failure Log In" , R.color.dark_red);
                    }
                }
            });
        }
    }

    public void SignUp(View view) {
        Intent intent = new Intent(MainActivity.this , SignUpActivity.class);
        startActivity(intent);
    }

    public void SignIn_With_Facbook_Account(View view) {
    }

    public void SignIn_With_Google_Account(View view) {
        signIn();
    }

    public void ForgetPassword(View view) {
        Intent intent = new Intent(MainActivity.this , ForgetPasswordActivity.class);
        startActivity(intent);
    }





    private void giveTextAndColorToTextView(View v , String text , int ColorID){
        TextView tv = (TextView)v;


        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                tv.setText(text);
                tv.setTextColor(getResources().getColor(ColorID));
                if(tv.getVisibility() == View.INVISIBLE){
                    tv.setVisibility(View.VISIBLE);
                }
            }
            public  void onFinish(){
                tv.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
}