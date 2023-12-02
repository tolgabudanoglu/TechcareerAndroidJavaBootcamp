package com.example.lezzetkapida.ui.foodOrder;

import static com.example.lezzetkapida.utils.Listeners.OrderToComplete;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.databinding.FragmentFoodOrderBinding;
import com.example.lezzetkapida.ui.foodOrder.adapter.FoodOrderAdapter;
import com.example.lezzetkapida.ui.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodOrderFragment extends Fragment {

    private FragmentFoodOrderBinding binding;
    private FoodOrderViewModel foodOrderViewModel;

    private FoodBasketUtils foodBasketUtils;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodOrderBinding.inflate(inflater, container, false);
        checkEmptyState();

        binding.rvFoodOrder.setLayoutManager(new LinearLayoutManager(requireContext()));
        FoodOrderAdapter adapter = new FoodOrderAdapter(requireContext(),foodOrderViewModel,FoodBasketUtils.getInstance().getBasketList(), this);
        adapter.setList(FoodBasketUtils.getInstance().getBasketList());
        binding.rvFoodOrder.setAdapter(adapter);






        binding.tvFoodOrderTotalPrice.setText(FoodBasketUtils.getInstance().getBasketListTotalPrice());

        foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getInstance().setBasketList(list));

        foodOrderViewModel.getDeletedInBasketLiveData().observe(getViewLifecycleOwner(), isDeleted -> {
            if (isDeleted) {
                foodOrderViewModel.getAllBasketFood("tolga");
            } else {
                Snackbar.make(requireView(), "Can't delete item!", Snackbar.LENGTH_SHORT).show();
            }
        });

        FoodBasketUtils.getInstance().getBasketLiveData().observe(getViewLifecycleOwner(), __ -> {
            checkEmptyState();
            adapter.setList(FoodBasketUtils.getInstance().getBasketList());
            Log.e("asd",FoodBasketUtils.getInstance().getBasketList().toString());
            binding.tvFoodOrderTotalPrice.setText(FoodBasketUtils.getInstance().getBasketListTotalPrice());
        });
        binding.btnFoodOrderConfirm.setOnClickListener(v -> {
            if (FoodBasketUtils.getInstance().getBasketList().size() > 0) {
                for (FoodBasket basket : FoodBasketUtils.getInstance().getBasketList()) {
                    foodOrderViewModel.deleteAllFood(basket.getBasketId(), "tolga");

                }
                Navigation.findNavController(OrderToComplete(v));

            } else {
                Snackbar.make(requireView(), "Please add food to your basket !", Snackbar.LENGTH_LONG).show();
            }
        });





        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodOrderViewModel = new ViewModelProvider(this).get(FoodOrderViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        checkEmptyState();


    }

    public void checkEmptyState() {
        if (FoodBasketUtils.getInstance().getBasketList().isEmpty()) {
            binding.rvFoodOrder.setVisibility(View.INVISIBLE);
            binding.tVEmptyState.setVisibility(View.VISIBLE);
            binding.iVEmptyState.setVisibility(View.VISIBLE);
        } else {
            binding.rvFoodOrder.setVisibility(View.VISIBLE);
            binding.tVEmptyState.setVisibility(View.INVISIBLE);
            binding.iVEmptyState.setVisibility(View.INVISIBLE);
        }
    }
}