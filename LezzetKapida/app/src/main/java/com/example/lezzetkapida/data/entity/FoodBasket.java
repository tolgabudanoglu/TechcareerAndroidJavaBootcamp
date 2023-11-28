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
    private String basketFoodName;
    @SerializedName("yemek_resim_adi")
    @Expose
    private String basketFoodImage;
    @SerializedName("yemek_fiyat")
    @Expose
    private int basketFoodPrice;
    @SerializedName("yemek_siparis_adet")
    @Expose
    private int basketOrderQuantity;
    @SerializedName("kullanici_adi")
    @Expose
    private String userName;

    public FoodBasket() {
    }

    public FoodBasket(int basketId, String basketFoodName, String basketFoodImage, int basketFoodPrice, int basketOrderQuantity, String userName) {
        this.basketId = basketId;
        this.basketFoodName = basketFoodName;
        this.basketFoodImage = basketFoodImage;
        this.basketFoodPrice = basketFoodPrice;
        this.basketOrderQuantity = basketOrderQuantity;
        this.userName = userName;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public String getBasketFoodName() {
        return basketFoodName;
    }

    public void setBasketFoodName(String basketFoodName) {
        this.basketFoodName = basketFoodName;
    }

    public String getBasketFoodImage() {
        return basketFoodImage;
    }

    public void setBasketFoodImage(String basketFoodImage) {
        this.basketFoodImage = basketFoodImage;
    }

    public int getBasketFoodPrice() {
        return basketFoodPrice;
    }

    public void setBasketFoodPrice(int basketFoodPrice) {
        this.basketFoodPrice = basketFoodPrice;
    }

    public int getBasketOrderQuantity() {
        return basketOrderQuantity;
    }

    public void setBasketOrderQuantity(int basketOrderQuantity) {
        this.basketOrderQuantity = basketOrderQuantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
