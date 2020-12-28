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

        titles.add("Computers");
        titles.add("Labtops");
        titles.add("Mobiles & Tablets");
        titles.add("Cameras");
        titles.add("Watches");
        titles.add("Televisions");
        titles.add("Projectors");
        titles.add("Men Clothes");
        titles.add("Books");
        titles.add("Sports");


        images.add(R.drawable.computer);
        images.add(R.drawable.labtop);
        images.add(R.drawable.mobile_and_tablet);
        images.add(R.drawable.camera);
        images.add(R.drawable.watche);
        images.add(R.drawable.tv);
        images.add(R.drawable.projector);
        images.add(R.drawable.men_clothes);
        images.add(R.drawable.book);
        images.add(R.drawable.sport);

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