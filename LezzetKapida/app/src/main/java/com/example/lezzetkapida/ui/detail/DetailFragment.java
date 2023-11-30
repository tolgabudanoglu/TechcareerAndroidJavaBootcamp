package com.example.lezzetkapida.ui.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.databinding.FragmentDetailBinding;
import com.example.lezzetkapida.ui.viewModel.DetailViewModel;
import com.example.lezzetkapida.ui.viewModel.HomeViewModel;
import com.example.lezzetkapida.utils.ImageLoaderHelper;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        DetailFragmentArgs bundle = DetailFragmentArgs.fromBundle(getArguments());
        Food food = bundle.getFood();


        ImageLoaderHelper.loadImage(requireContext(),binding.ivDetailFood,food.getImageName());
        binding.tvDetailFoodName.setText(food.getFoodName());
        binding.tvDetailFoodPrice.setText(food.getFoodPrice() +" ₺");

        binding.btnAddBasket.setOnClickListener(v -> {
            viewModel.addToBasket(food.getFoodName(),food.getImageName(),food.getFoodPrice(),1,"tolga");
        });










        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

    }
}