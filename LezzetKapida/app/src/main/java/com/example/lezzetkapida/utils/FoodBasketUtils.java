package com.example.lezzetkapida.utils;

import androidx.lifecycle.MutableLiveData;

import com.example.lezzetkapida.data.entity.FoodBasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodBasketUtils {
    private static FoodBasketUtils foodBasketUtils;
    private List<FoodBasket> basketList = new ArrayList<>();
    private MutableLiveData<Boolean> basketLiveData;

    private FoodBasketUtils() {

    }

    public void setBasketList(List<FoodBasket> basketList) {
        this.basketList = basketList;
        basketLiveData.postValue(true);
    }
    public List<FoodBasket> getBasketList() {
        return basketList;
    }

    public static FoodBasketUtils getItem() {
        if (foodBasketUtils == null)
            foodBasketUtils = new FoodBasketUtils();
        return foodBasketUtils;
    }
    public boolean hasItem(String foodName) {
        for (FoodBasket basket : basketList) {
            if (Objects.equals(foodName, basket.getFoodName())) {
                return true;
            }
        }
        return false;
    }

    public MutableLiveData<Boolean> getBasketLiveData() {
        if (basketLiveData == null)
            basketLiveData = new MutableLiveData<>();
        return basketLiveData;
    }





    public String getBasketListTotalPrice() {
        int totalPrice = 0;
        for (FoodBasket basket : basketList) {
            totalPrice += basket.getFoodOrderQuantity() * basket.getFoodPrice();
        }
        return totalPrice + " ₺ ";
    }
    public void clearBasketList() {
        basketList.clear();
    }



    public int basketFoodCount(String foodName) {
        for (FoodBasket basket : basketList) {
            if (Objects.equals(foodName, basket.getFoodName())) {
                return basket.getFoodOrderQuantity();
            }
        }
        return 0;
    }

    public int getBasketId(String foodName) {
        for (FoodBasket basket : basketList) {
            if (Objects.equals(foodName, basket.getFoodName())) {
                return basket.getBasketId();
            }
        }
        return 0;
    }
}

