package com.example.lezzetkapida.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Food implements Serializable {


    @SerializedName("yemek_id")
    @Expose
    private int id;
    @SerializedName("yemek_adi")
    @Expose
    private String foodName;
    @SerializedName("yemek_resim_adi")
    @Expose
    private String imageName;
    @SerializedName("yemek_fiyat")
    @Expose
    private int foodPrice;

    public Food() {
    }

    public Food(int id, String foodName, String imageName, int foodPrice) {
        this.id = id;
        this.foodName = foodName;
        this.imageName = imageName;
        this.foodPrice = foodPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }
}
