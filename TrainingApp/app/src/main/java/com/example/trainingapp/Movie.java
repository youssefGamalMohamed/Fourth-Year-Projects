package com.example.trainingapp;

public class Movie {

    private String name , description;

    public void setName(String name) {
        this.name = name;
    }

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
