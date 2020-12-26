package com.example.assignment1;

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

        Button btn_convert = (Button)findViewById(R.id.btn_convert);
        Button btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText first_input_edittext = (EditText)findViewById(R.id.editTextNumber1);
                EditText second_input_edittext = (EditText)findViewById(R.id.editTextNumber2);
                String str1 = first_input_edittext.getText().toString() , str2 = second_input_edittext.getText().toString();
                if(str1.equals("") && str2.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"No Currency Founded To Convert",Toast.LENGTH_LONG).show();
                }
                else if((!str1.equals("")) && (!str2.equals("")))
                {
                    Toast.makeText(getApplicationContext(),"Can Not Perform This Process",Toast.LENGTH_LONG).show();
                    first_input_edittext.setText("");
                    second_input_edittext.setText("");
                }
                else
                {
                    double x ;
                    if(str1.equals(""))
                    {
                        x = (Double.parseDouble(str2))/15.69;
                        first_input_edittext.setText(String.valueOf(x));
                    }
                    else
                    {
                        x = (Double.parseDouble(str1))*15.69;
                        second_input_edittext.setText(String.valueOf(x));
                    }

                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText first_input_edittext = (EditText)findViewById(R.id.editTextNumber1);
                EditText second_input_edittext = (EditText)findViewById(R.id.editTextNumber2);
                first_input_edittext.setText("");
                second_input_edittext.setText("");
            }
        });
    }
}