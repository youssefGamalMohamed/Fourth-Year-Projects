package com.example.souq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }


    public void SendCode(View view) {
        Intent intent = new Intent(ForgetPasswordActivity.this , ForgetPasswordWithCodeActivity.class);
        startActivity(intent);
    }
}