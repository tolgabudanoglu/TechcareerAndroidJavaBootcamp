package com.example.lezzetkapida.utils;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.ui.detail.DetailFragmentDirections;
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
    public static void detailToOrder(FoodBasket foodBasket,View view){
        DetailFragmentDirections.ActionDetailFragmentToFoodOrderFragment route = DetailFragmentDirections.actionDetailFragmentToFoodOrderFragment(foodBasket);
        Navigation.findNavController(view).navigate(route);
    }
    public static View SignUpToSignIn(View view){
        Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment);
        return view;
    }
    public static View signInToHome(View view){
        Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_homeFragment);
        return view;
    }
    public static View signInToSignUp(View view){
        Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment);
        return view;
    }
    public static View signOut(View view){
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_signInFragment);
        return view;
    }
    public static View HomeToOrderScreen(View view){
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_foodOrderFragment);
        return view;
    }







}
