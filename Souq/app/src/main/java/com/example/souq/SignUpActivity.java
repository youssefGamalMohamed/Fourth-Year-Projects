package com.example.souq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.validator.routines.EmailValidator;

public class SignUpActivity extends AppCompatActivity {
    private EditText  password , email;
    private TextView error_in_SignUP_or_Succefully;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        IntializeComponenet();
    }
    private void IntializeComponenet(){
        password = (EditText)(findViewById(R.id.signup_password));
        email = (EditText)(findViewById(R.id.signup_email));
        error_in_SignUP_or_Succefully = (TextView)(findViewById(R.id.error_in_signup));
        auth = FirebaseAuth.getInstance();
    }
    public void ConfirmSignUP(View view) {
        String password = this.password.getText().toString();
        String email = this.email.getText().toString();
        if(email.equals("") || password.equals("")){
            if(email.equals("")){
                giveTextAndColorToTextView((View)error_in_SignUP_or_Succefully , "Missing Email" , R.color.dark_red);
            }
            if(password.equals("")){

                giveTextAndColorToTextView((View)error_in_SignUP_or_Succefully , "Missing Password" , R.color.dark_red);
            }
            if(email.equals("") && password.equals("")){

                giveTextAndColorToTextView((View)error_in_SignUP_or_Succefully , "Missing Email and Password" , R.color.dark_red);
            }
        }
        else{
            boolean valid = EmailValidator.getInstance().isValid(email);
            if(valid){
                auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            giveTextAndColorToTextView((View)error_in_SignUP_or_Succefully , "Sign Up Successfully" , R.color.dark_green);
                            startActivity(new Intent(SignUpActivity.this , CategoriesActivity.class));
                        }else{
                            giveTextAndColorToTextView((View)error_in_SignUP_or_Succefully , "Sign Up Failed" , R.color.dark_red);
                        }

                    }
                });
            }
            else{
                giveTextAndColorToTextView((View)error_in_SignUP_or_Succefully , "Invalid Email or Password" , R.color.dark_red);
            }
        }
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