package com.example.lezzetkapida.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.data.repo.BasketRepository;
import com.example.lezzetkapida.data.repo.FoodRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel{
    public FoodRepository frepo;
    public MutableLiveData<List<Food>> foodList;
    public BasketRepository brepo;

    @Inject
    public HomeViewModel(FoodRepository frepo,BasketRepository brepo) {
        this.frepo = frepo;
        this.brepo = brepo;
        getAllFood();
        foodList = frepo.foodList;

    }

    public void getAllFood(){
        frepo.getAllFood();
    }

    public void addToBasket(String foodName, String foodImageName, int foodPrice, int foodQuantity, String userName){
        brepo.addFoodToBasket( foodName,  foodImageName,  foodPrice,  foodQuantity,  userName);
    }
}
