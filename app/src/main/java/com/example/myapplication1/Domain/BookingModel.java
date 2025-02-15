package com.example.myapplication1.Domain;

public class BookingModel {
    private String clientName;
    private String date;
    private String description;

    public BookingModel() {
        // Empty constructor for Firestore
    }

    public BookingModel(String clientName, String date, String description) {
        this.clientName = clientName;
        this.date = date;
        this.description = description;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
