package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convert_button = (Button)findViewById(R.id.convert_button);
        Button clear_button = (Button)findViewById(R.id.clear_button);
        EditText input1 = (EditText)findViewById(R.id.ed1);
        EditText input2 = (EditText)findViewById(R.id.ed2);
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = input1.getText().toString() , str2 = input2.getText().toString();
                if( (str1.equals("") && str2.equals("")) || ((!str1.equals("")) && (!str2.equals(""))))
                {
                    Toast.makeText(getApplicationContext(),"Invalid Process",Toast.LENGTH_LONG).show();
                    input1.setText("");
                    input2.setText("");
                }
                else
                {
                    double val ;
                    if(str1.equals(""))
                    {
                        val = (Double.parseDouble(str2))/15.69;
                        input1.setText(String.valueOf(val));
                    }
                    else
                    {
                        val = (Double.parseDouble(str1))*15.69;
                        input2.setText(String.valueOf(val));
                    }
                }
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input1.setText("");
                input2.setText("");
            }
        });






    }
}