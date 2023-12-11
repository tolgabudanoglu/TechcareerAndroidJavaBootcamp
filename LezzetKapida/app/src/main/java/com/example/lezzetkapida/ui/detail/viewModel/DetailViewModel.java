package com.example.lezzetkapida.ui.detail.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.data.repo.BasketRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {

    public BasketRepository brepo;
    private MutableLiveData<Boolean> inSame;
    private MutableLiveData<List<FoodBasket>> foodBasketList;

    private MutableLiveData<Boolean> deletedBasketLiveData;



    @Inject
    public DetailViewModel(BasketRepository brepo) {
        this.brepo = brepo;
    }

    public void addToBasket(String foodName, String foodImageName, int foodPrice, int foodQuantity, String userName){
        brepo.addFoodToBasket( foodName,  foodImageName,  foodPrice,  foodQuantity,  userName,inSame());
    }

    public MutableLiveData<Boolean> inSame() {
        if(inSame == null)
            inSame = new MutableLiveData<>();
        return inSame;
    }
    public MutableLiveData<List<FoodBasket>> getBasketList() {
        if(foodBasketList == null)
            foodBasketList = new MutableLiveData<>();
        return foodBasketList;
    }

    public MutableLiveData<Boolean> getDeletedInBasketLiveData() {
        if(deletedBasketLiveData == null)
            deletedBasketLiveData = new MutableLiveData<>();
        return deletedBasketLiveData;
    }
    public void deleteFood(int id , String userName){
        brepo.deleteFoodFromBasket(id,userName,getDeletedInBasketLiveData());
    }

}
