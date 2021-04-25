package com.example.souq.Classes;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Product {
    int ID;
    String Name;
    double price;
    int Quantity;
    String ImageUrl;

    public Product(String name, double price, int quantity, String imageUrl) {
        Name = name;
        this.price = price;
        Quantity = quantity;
        ImageUrl = imageUrl;
    }

    public Product(int ID, String name, double price, int quantity, String imageUrl) {
        this.ID = ID;
        Name = name;
        this.price = price;
        Quantity = quantity;
        ImageUrl = imageUrl;
    }
}
