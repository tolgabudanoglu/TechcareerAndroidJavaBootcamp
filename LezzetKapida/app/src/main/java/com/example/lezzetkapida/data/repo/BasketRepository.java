package com.example.lezzetkapida.data.repo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.lezzetkapida.data.entity.CRUDResponse;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.data.entity.FoodBasketResponse;
import com.example.lezzetkapida.retrofit.FoodDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketRepository {

    private FoodDao fdao;
    public MutableLiveData<List<FoodBasket>> basketList = new MutableLiveData<>();
    private Context mContext;


    public BasketRepository(FoodDao fdao) {
        this.fdao = fdao;
    }

    public void getBasket(String userName){
        fdao.getAllFoodBasket(userName).enqueue(new Callback<FoodBasketResponse>() {
            @Override
            public void onResponse(Call<FoodBasketResponse> call, Response<FoodBasketResponse> response) {
                List<FoodBasket> foods = response.body().getFoodBasketList();
                basketList.setValue(foods);
            }

            @Override
            public void onFailure(Call<FoodBasketResponse> call, Throwable t) {
                basketList.setValue(new ArrayList<>());
            }
        });
    }

    public void addFoodToBasket(String foodName, String foodImageName, int foodPrice, int foodQuantity, String userName ,MutableLiveData<Boolean> same){
        fdao.addFoodToBasket(foodName,foodImageName,foodPrice,foodQuantity,userName).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                if (response.isSuccessful()) {
                    same.setValue(true);
                } else {
                    same.setValue(false);
                }

            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {
                same.setValue(false);
            }
        });
    }

    public void deleteFoodFromBasket(int id,String userName,MutableLiveData<Boolean> deletedFood){
        fdao.deleteFoodFromBasket(id, userName).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                if (response.isSuccessful() && response.body().getSuccess() == 1)
                    deletedFood.setValue(true);
                else
                    deletedFood.setValue(false);
            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {
                deletedFood.setValue(false);
            }
        });
    }


}
