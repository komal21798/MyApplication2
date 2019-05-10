package com.komal.myapplication;

public class Bookings {

    private int id;
    private String room_title;
    private int quantity;
    private double total;
    private String username;


    public int getId() {
        return id;
    }

    public String getRoom_title() {
        return room_title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public String getUsername() {
        return username;
    }


    public Bookings(int id, String room_title, int quantity, double total, String username) {
        this.id = id;
        this.room_title = room_title;
        this.quantity = quantity;
        this.total = total;
        this.username = username;
    }
}
