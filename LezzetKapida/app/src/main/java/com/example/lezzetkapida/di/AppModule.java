package com.example.lezzetkapida.di;

import com.example.lezzetkapida.data.repo.FoodRepository;
import com.example.lezzetkapida.retrofit.FoodDao;

public class AppModule {

    public FoodRepository provideFoodRepository(FoodDao foodDao){
        return new FoodRepository(foodDao);
    }
}
