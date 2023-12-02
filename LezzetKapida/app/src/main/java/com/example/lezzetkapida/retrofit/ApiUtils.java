package com.example.lezzetkapida.retrofit;

import static com.example.lezzetkapida.utils.Constants.BASE_URL;

public class ApiUtils {



    public static FoodDao getFoodDao(){
        return RetrofitClient.getClient(BASE_URL).create(FoodDao.class);
    }
}
