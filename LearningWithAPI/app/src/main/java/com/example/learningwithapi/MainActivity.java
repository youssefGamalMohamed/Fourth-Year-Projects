package com.example.learningwithapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String URL_DATA = "https://api.thecatapi.com/v1/breeds";
    RecyclerView rv;
    ArrayList<Cat> catArrayList;
    CatRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recycler_view1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        catArrayList = new ArrayList<>();

        InitializeAll();
    }

    private void InitializeAll(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0 ; i < response.length() ; i++)
                        {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("image");

                                Cat cat = new Cat(jsonObject.getString("name") ,
                                                  jsonObject.getString("origin") ,
                                                  jsonObject.getString("life_span"),
                                                  jsonObject1.getString("url"),
                                                  jsonObject.getString("temperament"),
                                                  jsonObject.getString("description"));
                                catArrayList.add(cat);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new CatRecyclerViewAdapter(MainActivity.this , catArrayList);
                        rv.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this , "Can not Get Data" , Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

}