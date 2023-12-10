package com.example.lezzetkapida.ui.orderComplete;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lezzetkapida.databinding.FragmentOrderCompleteBinding;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.example.lezzetkapida.utils.Listeners;


public class OrderCompleteFragment extends Fragment {

    private FragmentOrderCompleteBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderCompleteBinding.inflate(inflater, container, false);

        binding.button.setOnClickListener(v -> {
            FoodBasketUtils.getItem().clearBasketList();
            Listeners.orderCompleteToHome(v);
        });



        return binding.getRoot();
    }
}