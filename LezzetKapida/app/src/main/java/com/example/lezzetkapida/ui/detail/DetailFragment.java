package com.example.lezzetkapida.ui.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.databinding.FragmentDetailBinding;
import com.example.lezzetkapida.ui.viewModel.DetailViewModel;
import com.example.lezzetkapida.ui.viewModel.HomeViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.example.lezzetkapida.utils.ImageLoaderHelper;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;
    private Food food;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);




        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DetailFragmentArgs bundle = DetailFragmentArgs.fromBundle(getArguments());
        Food food = bundle.getFood();

        ImageLoaderHelper.loadImage(requireContext(),binding.ivDetailFood,food.getImageName());
        binding.tvDetailFoodName.setText(food.getFoodName());
        binding.tvDetailFoodPrice.setText(food.getFoodPrice() +" ₺");

        if (FoodBasketUtils.getInstance().hasItem(food.getFoodName())) {
            int foodCount = FoodBasketUtils.getInstance().basketFoodCount(food.getFoodName());
            Log.e("sayı", String.valueOf(foodCount));
            binding.tvDetailQuantity.setText(String.valueOf(foodCount));
        } else {
            binding.tvDetailQuantity.setText("1");
        }

        binding.btnDetailAdd.setOnClickListener(v -> {
            String amount = binding.tvDetailQuantity.getText().toString();
            if (amount.isEmpty()) {
                amount = "0";
            }
            int newAmount= Integer.parseInt(amount);
            binding.tvDetailQuantity.setText(String.valueOf(newAmount + 1));
        });

        binding.btnSubstract.setOnClickListener(view1 -> {
            String amount = binding.tvDetailQuantity.getText().toString();
            int foodAmount = 1;
            if (amount.isEmpty()) {
                amount = "1";
            }

            if (Integer.parseInt(amount) > 1) {
                foodAmount = Integer.parseInt(amount) - 1;
            }

            binding.tvDetailQuantity.setText(String.valueOf(foodAmount));
        });
        binding.btnAddBasket.setOnClickListener(v -> {
            if (FoodBasketUtils.getInstance().hasItem(food.getFoodName())) {
                Toast.makeText(getContext(), "bu ürün var zaten", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.addToBasket(food.getFoodName(), food.getImageName(), food.getFoodPrice(), Integer.parseInt(binding.tvDetailQuantity.getText().toString()), "tolga");
            }
        });
        viewModel.inSame().observe(getViewLifecycleOwner(),inSame->{
            if (inSame){
                viewModel.getBasketListLiveData();
            }else {
                Snackbar.make(requireView(), "Please add food to your basket", Snackbar.LENGTH_SHORT).show();
            }
        });

        viewModel.getBasketListLiveData().observe(getViewLifecycleOwner(),list->{
            FoodBasketUtils.getInstance().setBasketList(list);
        });

        viewModel.getDeletedInBasketLiveData().observe(getViewLifecycleOwner(),__->{
            viewModel.addToBasket(food.getFoodName(),food.getImageName(),food.getFoodPrice(),Integer.parseInt(binding.tvDetailQuantity.getText().toString()),"tolga");
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

    }
}