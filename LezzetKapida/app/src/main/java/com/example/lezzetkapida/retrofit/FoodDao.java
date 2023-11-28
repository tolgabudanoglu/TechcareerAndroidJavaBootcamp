package com.example.lezzetkapida.retrofit;

import com.example.lezzetkapida.data.entity.CRUDResponse;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.data.entity.FoodBasketResponse;
import com.example.lezzetkapida.data.entity.FoodResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodDao {

    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php
    //http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php
    //http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php

    @GET("yemekler/tumYemekleriGetir.php")
    Call<FoodResponse> getAllFood();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDResponse> addFoodToBasket(@Field("yemek_adi") String foodName,
                                       @Field("yemek_resim_adi") String foodImageName,
                                       @Field("yemek_fiyat") int foodPrice,
                                       @Field("yemek_siparis_adet") int foodOrderQuantity,
                                       @Field("kullanici_adi") String userName);

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<FoodBasketResponse> getAllFoodBasket(@Field("kullanici_adi") String userName);


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDResponse> deleteFoodFromBasket(@Field("sepet_yemek_id") int foodId, @Field("kullanici_adi") String userName);


}
