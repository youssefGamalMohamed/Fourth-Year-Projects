package com.example.employeeassignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();
        ArrayList<Employee> arr = (ArrayList<Employee>)b.getSerializable("myemployee") ;
        RecyclerView rv = findViewById(R.id.employeerecyclerview);
        MyEmployeeRecyclerViewAdater adapter = new MyEmployeeRecyclerViewAdater(arr);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());

        DividerItemDecoration divider = new DividerItemDecoration(rv.getContext() , DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(),R.drawable.space_between_items_of_recyclervieww));
        rv.addItemDecoration(divider);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }
}