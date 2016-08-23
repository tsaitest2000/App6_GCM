package com.example.testfragdrawer;

public class Pirate {

   private String type;
   private String name;
   private String imageUrl;

   public Pirate(String type, String name, String imageUrl) {
      this.type = type;
      this.name = name;
      this.imageUrl = imageUrl;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

}
