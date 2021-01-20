package com.example.learningwithapi;

public class Cat {
    private  String Name , Origin , Life_Span , ImageUrl , Temperature , Description;

    public Cat(String name, String origin, String life_Span, String imageUrl, String temperature, String description) {
        Name = name;
        Origin = origin;
        Life_Span = life_Span;
        ImageUrl = imageUrl;
        Temperature = temperature;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getLife_Span() {
        return Life_Span;
    }

    public void setLife_Span(String life_Span) {
        Life_Span = life_Span;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
