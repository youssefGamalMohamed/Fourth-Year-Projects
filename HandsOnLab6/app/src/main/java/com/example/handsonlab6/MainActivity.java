package com.example.handsonlab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final MovieDataBaseClass mydb = new MovieDataBaseClass(getApplicationContext());
        final EditText editext_name = (EditText)(findViewById(R.id.editext_name));
        final EditText editext_description = (EditText)(findViewById(R.id.editext_description));
        final Button btn_addmovie = (Button)(findViewById(R.id.btn_addmovie));
        final Button btn_showallmovies = (Button)(findViewById(R.id.btn_showallmovies));


        btn_addmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editext_name.getText().toString();
                String description = editext_description.getText().toString();
                mydb.Insertmovie(name , description);
            }
        });


        btn_showallmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication() , ShowMovies.class);
                startActivity(i);
            }
        });

    }
}