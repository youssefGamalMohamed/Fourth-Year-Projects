package com.example.trainingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMoviesActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        rv = findViewById(R.id.myrecyclerview);

        ArrayList<Movie> allmovies = new ArrayList<>();
        allmovies.add(new Movie("aa","dsdsdsd"));
        allmovies.add(new Movie("bb","dsdsdsd"));
        allmovies.add(new Movie("cc","dsdsdsd"));
        allmovies.add(new Movie("dd","dsdsdsd"));
        allmovies.add(new Movie("ee","dsdsdsd"));
        allmovies.add(new Movie("ff","dsdsdsd"));
        allmovies.add(new Movie("aa","dsdsdsd"));
        allmovies.add(new Movie("bb","dsdsdsd"));
        allmovies.add(new Movie("cc","dsdsdsd"));
        allmovies.add(new Movie("dd","dsdsdsd"));
        allmovies.add(new Movie("ee","dsdsdsd"));
        allmovies.add(new Movie("ff","dsdsdsd"));
        allmovies.add(new Movie("aa","dsdsdsd"));
        allmovies.add(new Movie("bb","dsdsdsd"));
        allmovies.add(new Movie("cc","dsdsdsd"));
        allmovies.add(new Movie("dd","dsdsdsd"));
        allmovies.add(new Movie("ee","dsdsdsd"));
        allmovies.add(new Movie("ff","dsdsdsd"));
        allmovies.add(new Movie("aa","dsdsdsd"));
        allmovies.add(new Movie("bb","dsdsdsd"));
        allmovies.add(new Movie("cc","dsdsdsd"));
        allmovies.add(new Movie("dd","dsdsdsd"));
        allmovies.add(new Movie("ee","dsdsdsd"));
        allmovies.add(new Movie("ff","dsdsdsd"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(allmovies);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(this , DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(mDividerItemDecoration);
    }
}