package com.example.myapplication1.Domain;

public class ElectricDomain {
    private String name;
    private String experience;
    private String picPath;
    private float rating;
    private int ratingCount;

    public ElectricDomain(String name, String experience, String picPath, float rating, int ratingCount) {
        this.name = name;
        this.experience = experience;
        this.picPath = picPath;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }


}
