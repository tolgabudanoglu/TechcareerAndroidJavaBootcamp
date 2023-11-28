package com.example.lezzetkapida.retrofit;

public class ApiUtils {

    public static final String BASE_URL = "http://kasimadalan.pe.hu/";

    public static FoodDao getFoodDao(){
        return RetrofitClient.getClient(BASE_URL).create(FoodDao.class);
    }
}
