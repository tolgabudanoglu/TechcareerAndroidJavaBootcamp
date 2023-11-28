package com.example.lezzetkapida.utils;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.ui.detail.DetailFragmentArgs;
import com.example.lezzetkapida.ui.home.HomeFragmentDirections;

public class Listeners {

    public static void homeToDetail(Food food,View view){
        HomeFragmentDirections.ActionHomeFragmentToDetailFragment route = HomeFragmentDirections.actionHomeFragmentToDetailFragment(food);
        Navigation.findNavController(view).navigate(route);
    }



}
