package com.example.lezzetkapida.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.data.repo.FoodRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodOrderViewModel extends ViewModel {
    public FoodRepository frepo;
    public MutableLiveData<List<FoodBasket>> foodBasketList;

    @Inject
    public FoodOrderViewModel(FoodRepository frepo, MutableLiveData<List<FoodBasket>> foodBasketList) {
        this.frepo = frepo;
        this.foodBasketList = foodBasketList;
    }
}
