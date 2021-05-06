package com.example.foody.model;

public class Cart {
    String name;
    String price;
    Integer imageUrl;
    String quantity;
    public Cart(String name, String price, Integer imageUrl, String quantity) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity=quantity;
    }




    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

}
