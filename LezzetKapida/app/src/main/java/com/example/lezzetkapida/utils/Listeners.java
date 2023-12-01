package com.example.lezzetkapida.utils;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.ui.home.HomeFragmentDirections;

public class Listeners {

    public static void homeToDetail(Food food,View view){
        HomeFragmentDirections.ActionHomeFragmentToDetailFragment route = HomeFragmentDirections.actionHomeFragmentToDetailFragment(food);
        Navigation.findNavController(view).navigate(route);
    }

    public static View OrderToComplete(View view){
        Navigation.findNavController(view).navigate(R.id.action_foodOrderFragment_to_orderCompleteFragment);
        return view;
    }







}
