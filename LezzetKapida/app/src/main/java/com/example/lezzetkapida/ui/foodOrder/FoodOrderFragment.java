package com.example.lezzetkapida.ui.foodOrder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.databinding.FragmentFoodOrderBinding;
import com.example.lezzetkapida.ui.foodOrder.adapter.FoodOrderAdapter;
import com.example.lezzetkapida.ui.home.adapter.HomeAdapter;
import com.example.lezzetkapida.ui.viewModel.FoodOrderViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodOrderFragment extends Fragment {

    private FragmentFoodOrderBinding binding;
    private FoodOrderViewModel foodOrderViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodOrderBinding.inflate(inflater, container, false);



        binding.rvFoodOrder.setLayoutManager(new LinearLayoutManager(requireContext()));

        foodOrderViewModel.foodBasketList.observe(getViewLifecycleOwner(),foodBasketList ->{
            FoodOrderAdapter adapter = new FoodOrderAdapter(requireContext(),foodOrderViewModel,foodBasketList);
            binding.rvFoodOrder.setAdapter(adapter);
        });




        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodOrderViewModel = new ViewModelProvider(this).get(FoodOrderViewModel.class);
    }
}