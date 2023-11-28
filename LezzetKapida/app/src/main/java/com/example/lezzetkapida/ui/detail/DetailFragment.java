package com.example.lezzetkapida.ui.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.databinding.FragmentDetailBinding;
import com.example.lezzetkapida.utils.ImageLoaderHelper;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        DetailFragmentArgs bundle = DetailFragmentArgs.fromBundle(getArguments());
        Food food = bundle.getFood();


        ImageLoaderHelper.loadImage(requireContext(),binding.ivDetailFood,food.getImageName());
        binding.tvDetailFoodName.setText(food.getFoodName());
        binding.tvDetailFoodPrice.setText(food.getFoodPrice() +" ₺");








        return binding.getRoot();
    }
}