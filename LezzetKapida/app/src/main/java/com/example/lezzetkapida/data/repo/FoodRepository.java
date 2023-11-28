package com.example.lezzetkapida.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.entity.FoodResponse;
import com.example.lezzetkapida.retrofit.ApiUtils;
import com.example.lezzetkapida.retrofit.FoodDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

    public MutableLiveData<List<Food>> foodList = new MutableLiveData<>();
    private FoodDao fdao;

    public FoodRepository(FoodDao fdao) {
        this.fdao = fdao;
    }

    public void getAllFood(){
        fdao.getAllFood().enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                List<Food> foods = response.body().getFoodList();
                foodList.setValue(foods);
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {

            }
        });
    }
}
