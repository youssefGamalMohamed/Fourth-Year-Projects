package com.example.souq.Classes;

import java.util.ArrayList;

public class Cart {

    public static class Item{
        private  int productid;
        private  int quantity;

        public Item(int productid, int quantity) {
            this.productid = productid;
            this.quantity = quantity;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }


    private int id , userId;
    private String date;
    private ArrayList<Item> products;

    public Cart(int id, int userId, String date, ArrayList<Item> products) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Item> products) {
        this.products = products;
    }
}
