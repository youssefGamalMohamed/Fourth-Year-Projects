package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText ed_name = findViewById(R.id.signup_name);
        EditText ed_gender = findViewById(R.id.signup_gender);
        EditText ed_birthdate = findViewById(R.id.signup_birthdate);
        EditText ed_job = findViewById(R.id.signup_job);
        EditText ed_username = findViewById(R.id.signup_username);
        EditText ed_password = findViewById(R.id.signup_password);
        EditText ed_question = findViewById(R.id.signup_recoveryquesiton);
        EditText ed_answer = findViewById(R.id.signup_recoveryanswer);

        TextView tv = (TextView)(findViewById(R.id.signup_wait_asceond_and_successfully_));

        String name = ed_name.getText().toString();
        String gender = ed_gender.getText().toString();
        String job = ed_job.getText().toString();
        String birthdate = ed_birthdate.getText().toString();
        String username = ed_username.getText().toString();
        String password = ed_password.getText().toString();
        String question = ed_question.getText().toString();
        String answer = ed_answer.getText().toString();


        EcommerceDataBase dbobj = new EcommerceDataBase(getApplicationContext());

        Customer c = new Customer(name , username , password , birthdate , gender , job , question , answer);


        Button btn_confirm = (Button)(findViewById(R.id.btn_confirm_data_in_sign_up));
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbobj.SignUp(c);
                new CountDownTimer(4000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tv.setText("Wait a Second...............");
                    }

                    public void onFinish() {
                        tv.setText("Successfully Sign Up");
                    }
                }.start();
            }
        });

    }
}