package com.example.handsonlab6;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        MovieDataBaseClass mydb = new MovieDataBaseClass(getApplicationContext());
        ListView listview1 = (ListView)(findViewById(R.id.listview1));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext() , android.R.layout.simple_list_item_1);
        listview1.setAdapter(adapter);


        Cursor c = mydb.getAllmovies();
        while(!c.isAfterLast())
        {
            adapter.add(c.getString(1));
            c.moveToNext();
        }

    }
}