package com.example.trainingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MovieHolder> {
    ArrayList<Movie> movies;

    public RecyclerViewAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_custom_item,null,false);
        MovieHolder mh = new MovieHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie m = movies.get(position);
        String x = "Name : " + m.getName(), y = "Description : " + m.getDescription();
        holder.txt_name.setText(x);
        holder.txt_desc.setText(y);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        TextView txt_name , txt_desc;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.customitem_textview1);
            txt_desc = itemView.findViewById(R.id.customitem_textview2);
        }
    }
}
