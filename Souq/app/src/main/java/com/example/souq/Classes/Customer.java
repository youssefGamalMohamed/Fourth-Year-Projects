package com.example.souq.Classes;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Customer {
    int ID;
    String Name;
    String Username;
    String Password;
    String Gender;
    String Birthdate;
    String Job;

    public Customer(String name, String username, String password, String gender, String birthdate, String job) {
        Name = name;
        Username = username;
        Password = password;
        Gender = gender;
        Birthdate = birthdate;
        Job = job;
    }

    public Customer(int ID, String name, String username, String password, String gender, String birthdate, String job) {
        this.ID = ID;
        Name = name;
        Username = username;
        Password = password;
        Gender = gender;
        Birthdate = birthdate;
        Job = job;
    }
}
