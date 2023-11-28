package com.example.lezzetkapida.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Food implements Serializable {

    @SerializedName("yemek_id")
    @Expose
    private int foodId;
    @SerializedName("yemek_adi")
    @Expose
    private String foodName;
    @SerializedName("yemek_resim_adi")
    @Expose
    private String foodImage;
    @SerializedName("yemek_fiyat")
    @Expose
    private int foodPrice;

    public Food() {
    }

    public Food(int foodId, String foodName, String footImage, int foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImage = footImage;
        this.foodPrice = foodPrice;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }
}
