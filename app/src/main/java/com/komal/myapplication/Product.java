package com.komal.myapplication;

public class Product {

    private int id;
    private String title;
    private String shortdesc;
    private double price;
    private String image;

    public Product(int id, String title, String shortdesc, double price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }


    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}



