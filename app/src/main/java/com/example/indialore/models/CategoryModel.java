package com.example.indialore.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    String img_url;
    String name;

    public CategoryModel(String img_url, String name) {
        this.img_url = img_url;
        this.name = name;
    }
    public CategoryModel(){

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
}
