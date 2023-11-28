package com.example.lezzetkapida.di;

import com.example.lezzetkapida.data.repo.BasketRepository;
import com.example.lezzetkapida.data.repo.FoodRepository;
import com.example.lezzetkapida.retrofit.ApiUtils;
import com.example.lezzetkapida.retrofit.FoodDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public FoodRepository provideFoodRepository(FoodDao foodDao){
        return new FoodRepository(foodDao);
    }

    @Singleton
    @Provides
    public BasketRepository provideBasketRepository(FoodDao foodDao){
        return new BasketRepository(foodDao);
    }
    @Provides
    @Singleton
    public FoodDao provideFoodDao(){
        return ApiUtils.getFoodDao();
    }
}
