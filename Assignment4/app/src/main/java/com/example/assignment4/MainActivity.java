package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtview3 = (TextView)(findViewById(R.id.textView3));
        registerForContextMenu(txtview3);

        Button btn = (Button)(findViewById(R.id.button1));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText number1 = (EditText)(findViewById(R.id.editTextNumber1));
                EditText number2 = (EditText)(findViewById(R.id.editTextNumber2));
                EditText number3 = (EditText)(findViewById(R.id.editTextNumber3));
                number1.setText("");
                number2.setText("");
                number3.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText number1 = (EditText)(findViewById(R.id.editTextNumber1));
        EditText number2 = (EditText)(findViewById(R.id.editTextNumber2));
        EditText number3 = (EditText)(findViewById(R.id.editTextNumber3));
        String strv1 , strv2;
        strv1 = number1.getText().toString();
        strv2 = number2.getText().toString();
        if(strv1.equals("") || strv2.equals("")) {
            Toast.makeText(getApplicationContext(),"Missing One Input Field!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        double x , y , res;
        x = Double.parseDouble(strv1);
        y = Double.parseDouble(strv2);


        switch (item.getItemId())
        {
            case R.id.Div:
                res = x / y;
                number3.setText(String.valueOf(res));
                return true;
            case R.id.Mul:
                res = x * y;
                number3.setText(String.valueOf(res));
                return true;
            case R.id.Sub:
                res = x - y;
                number3.setText(String.valueOf(res));
                return true;
            case R.id.Add:
                res = x + y;
                number3.setText(String.valueOf(res));
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.mymenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        EditText number1 = (EditText)(findViewById(R.id.editTextNumber1));
        EditText number2 = (EditText)(findViewById(R.id.editTextNumber2));
        EditText number3 = (EditText)(findViewById(R.id.editTextNumber3));
        String strv1 , strv2;
        strv1 = number1.getText().toString();
        strv2 = number2.getText().toString();
        if(strv1.equals("") || strv2.equals("")) {
            Toast.makeText(getApplicationContext(),"Missing One Input Field!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        double x , y , res;
        x = Double.parseDouble(strv1);
        y = Double.parseDouble(strv2);


        switch (item.getItemId())
        {
            case R.id.Div:
                res = x / y;
                number3.setText(String.valueOf(res));
                return true;
            case R.id.Mul:
                res = x * y;
                number3.setText(String.valueOf(res));
                return true;
            case R.id.Sub:
                res = x - y;
                number3.setText(String.valueOf(res));
                return true;
            case R.id.Add:
                res = x + y;
                number3.setText(String.valueOf(res));
                return true;
        }
        return false;
    }
}