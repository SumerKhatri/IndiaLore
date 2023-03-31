package com.example.indialore.models;

import java.io.Serializable;

public class ProductModel implements Serializable {
    String img_url,name,description,state;
    int price;
    public ProductModel(){

    }
    public ProductModel(String img_url, String name, String description, String state, int price) {
        this.img_url = img_url;
        this.name = name;
        this.description = description;
        this.state = state;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
