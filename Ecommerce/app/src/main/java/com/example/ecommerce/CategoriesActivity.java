package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {
    RecyclerView categories_recyceler_view ;
    CategoriesArrayAdapter adapter;
    ArrayList<String> titles;
    ArrayList<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        categories_recyceler_view = findViewById(R.id.categories_recyclerview);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("First Image");
        titles.add("Second Image");
        titles.add("Third Image");
        titles.add("Fourth Image");

        images.add(R.drawable.cartimage);
        images.add(R.drawable.jumiaimage);
        images.add(R.drawable.lockicon);
        images.add(R.drawable.usericon);

        adapter = new CategoriesArrayAdapter(titles , images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext() , 2 , GridLayoutManager.VERTICAL , false);
        categories_recyceler_view.setAdapter(adapter);
        categories_recyceler_view.setLayoutManager(gridLayoutManager);
        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        categories_recyceler_view.addItemDecoration(new CategoriesItemDecoration(spanCount, spacing, includeEdge));
    }
}