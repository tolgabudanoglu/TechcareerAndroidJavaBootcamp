package com.example.lezzetkapida.ui.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.lezzetkapida.data.repo.BasketRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {

    public BasketRepository brepo;
    @Inject
    public DetailViewModel(BasketRepository brepo) {
        this.brepo = brepo;
    }

    public void addToBasket(String foodName, String foodImageName, int foodPrice, int foodQuantity, String userName){
        brepo.addFoodToBasket( foodName,  foodImageName,  foodPrice,  foodQuantity,  userName);
    }
}
