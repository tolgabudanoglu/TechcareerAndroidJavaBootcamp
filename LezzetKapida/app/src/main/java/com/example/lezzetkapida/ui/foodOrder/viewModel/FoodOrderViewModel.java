package com.example.lezzetkapida.ui.foodOrder.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.data.repo.BasketRepository;
import com.example.lezzetkapida.data.repo.FoodRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FoodOrderViewModel extends ViewModel {
    public BasketRepository brepo;
    public MutableLiveData<List<FoodBasket>> foodBasketList;
    private MutableLiveData<Boolean> deletedBasketLiveData;


    @Inject
    public FoodOrderViewModel(BasketRepository brepo) {
        this.brepo = brepo;
        getAllBasketFood();
        foodBasketList = brepo.basketList;

    }

    public void getAllBasketFood(){
        brepo.getBasket();

    }
    public MutableLiveData<List<FoodBasket>> getBasketListLiveData() {
        if(foodBasketList == null)
            foodBasketList = new MutableLiveData<>();
        return foodBasketList;
    }

    public void deleteFood(int id , String userName){
        brepo.deleteFoodFromBasket(id,userName,getDeletedInBasketLiveData());
    }

    public MutableLiveData<Boolean> getDeletedInBasketLiveData() {
        if(deletedBasketLiveData == null)
            deletedBasketLiveData = new MutableLiveData<>();
        return deletedBasketLiveData;
    }

    public void deleteAllFood(int id, String userName){
        List<FoodBasket> basketList = foodBasketList.getValue();

        if (basketList != null) {
            for (FoodBasket basket : basketList) {
                brepo.deleteFoodFromBasket(id, userName, getDeletedInBasketLiveData());
            }
        }
    }




}