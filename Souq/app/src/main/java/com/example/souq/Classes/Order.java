package com.example.souq.Classes;


public class Order {
    int ID;
    String Date;
    String Address;

    public Order(String date, String address) {
        Date = date;
        Address = address;
    }

    public Order(int ID, String date, String address) {
        this.ID = ID;
        Date = date;
        Address = address;
    }
}
