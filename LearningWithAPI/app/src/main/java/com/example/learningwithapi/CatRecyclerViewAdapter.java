package com.example.learningwithapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.ViewHolder> {

    ArrayList<Cat> catArrayList;
    Context context;

    public CatRecyclerViewAdapter(Context context , ArrayList<Cat> catArrayList) {
        this.catArrayList = catArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_recyclerview_item , parent , false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(catArrayList.get(position).getName());
        holder.tv_origin.setText(catArrayList.get(position).getOrigin());
        holder.tv_temperature.setText(catArrayList.get(position).getTemperature());
        holder.tv_description.setText(catArrayList.get(position).getDescription());
        holder.tv_life_span.setText(catArrayList.get(position).getLife_Span());
        Picasso.get()
                .load(catArrayList.get(position).getImageUrl())
                .into(holder.img_cat);
    }

    @Override
    public int getItemCount() {
        return catArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name , tv_temperature , tv_origin , tv_description , tv_life_span;
        ImageView img_cat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.cat_name);
            tv_description = itemView.findViewById(R.id.cat_description);
            tv_temperature = itemView.findViewById(R.id.cat_temperature);
            tv_origin = itemView.findViewById(R.id.cat_origin);
            tv_life_span = itemView.findViewById(R.id.cat_life_span);
            img_cat = itemView.findViewById(R.id.cat_image);
        }
    }
}
