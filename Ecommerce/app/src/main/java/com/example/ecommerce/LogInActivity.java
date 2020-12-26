package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btn_signup = (Button)(findViewById(R.id.btn_signup_in_loginactivity));
        TextView forgetpasswordtv = (TextView)(findViewById(R.id.login_forgetpassword_textview));


        EditText ed_username = findViewById(R.id.login_username);
        EditText ed_password = findViewById(R.id.login_password);
        Button btn_login = (Button)(findViewById(R.id.btn_login));
        EcommerceDataBase dbobj = new EcommerceDataBase(getApplicationContext());


        TextView tv_error = (TextView)(findViewById(R.id.incorrect_username_or_password));


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString();
                String password = ed_password.getText().toString();
                boolean Exist_Customer_or_Not = dbobj.Login(username , password);
                if(Exist_Customer_or_Not == false){
                    if(tv_error.getVisibility() == View.INVISIBLE)
                        tv_error.setVisibility(View.VISIBLE);
                }
                else {
                    if(tv_error.getVisibility() == View.VISIBLE)
                        tv_error.setVisibility(View.INVISIBLE);

                    // then go to the e-commerce app
                }
            }
        });

        forgetpasswordtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ForgetPasswordActivity.class);
                startActivity(i);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}