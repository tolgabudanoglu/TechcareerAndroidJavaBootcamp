package com.example.lezzetkapida.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FoodBasket implements Serializable {

    @SerializedName("sepet_yemek_id")
    @Expose
    private int basketId;
    @SerializedName("yemek_adi")
    @Expose
    private String foodName;
    @SerializedName("yemek_resim_adi")
    @Expose
    private String foodImageName;
    @SerializedName("yemek_fiyat")
    @Expose
    private int foodPrice;
    @SerializedName("yemek_siparis_adet")
    @Expose
    private int foodOrderQuantity;
    @SerializedName("kullanici_adi")
    @Expose
    private String userName;

    public FoodBasket() {
    }

    public FoodBasket(int basketId, String foodName, String foodImageName, int foodPrice, int foodOrderQuantity, String userName) {
        this.basketId = basketId;
        this.foodName = foodName;
        this.foodImageName = foodImageName;
        this.foodPrice = foodPrice;
        this.foodOrderQuantity = foodOrderQuantity;
        this.userName = userName;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImageName() {
        return foodImageName;
    }

    public void setFoodImageName(String foodImageName) {
        this.foodImageName = foodImageName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodOrderQuantity() {
        return foodOrderQuantity;
    }

    public void setFoodOrderQuantity(int foodOrderQuantity) {
        this.foodOrderQuantity = foodOrderQuantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
