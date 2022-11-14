package com.example.finalproject2;

public class Products {

   private String name;
   private String image;
   private String categoryName;
   private Integer Qty;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Products(String id) {
        Id = id;
    }

    private String Id;

   public Products(){

   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }


    public Products(String name, String image, String categoryName, Integer qty) {
        this.name = name;
        this.image = image;
        this.categoryName = categoryName;
        Qty = qty;
    }


}
