package com.example.lezzetkapida.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.repo.FoodRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel{
    public FoodRepository frepo;
    public MutableLiveData<List<Food>> foodList;

    @Inject
    public HomeViewModel(FoodRepository frepo) {
        this.frepo = frepo;
        getAllFood();
        foodList = frepo.foodList;
    }

    public void getAllFood(){
        frepo.getAllFood();
    }
}
