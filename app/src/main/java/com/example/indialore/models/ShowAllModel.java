package com.example.indialore.models;

import java.io.Serializable;

public class ShowAllModel implements Serializable {
   String name;
   String img_url;

   public ShowAllModel(){

   }
   public ShowAllModel(String name, String img_url) {
      this.name = name;
      this.img_url = img_url;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImg_url() {
      return img_url;
   }

   public void setImg_url(String img_url) {
      this.img_url = img_url;
   }
}
