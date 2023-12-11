package com.example.lezzetkapida.ui.foodOrder;

import static com.example.lezzetkapida.utils.Listeners.OrderToComplete;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.lezzetkapida.ui.foodOrder.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.example.lezzetkapida.utils.Listeners;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodOrderFragment extends Fragment {

    private FragmentFoodOrderBinding binding;
    private FoodOrderViewModel foodOrderViewModel;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;

    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodOrderBinding.inflate(inflater, container, false);
        checkEmptyState();

        binding.rvFoodOrder.setLayoutManager(new LinearLayoutManager(requireContext()));




        binding.basketToolbar.setNavigationOnClickListener(v -> {
            Listeners.OrderScreenToHome(v);

        });







        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        Log.e("user",firebaseUser.getEmail().toString());








        foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getItem().setBasketList(list));

        foodOrderViewModel.getDeletedInBasketLiveData().observe(getViewLifecycleOwner(), isDeleted -> {
            if (isDeleted) {
                foodOrderViewModel.getAllBasketFood();
            } else {
                Snackbar.make(requireView(), "Hata oldu", Snackbar.LENGTH_SHORT).show();
            }
        });


        binding.btnFoodOrderConfirm.setOnClickListener(v -> {
            if (FoodBasketUtils.getItem().getBasketList().size() > 0) {
                for (FoodBasket basket : FoodBasketUtils.getItem().getBasketList()) {
                    foodOrderViewModel.deleteAllFood(basket.getBasketId(), firebaseUser.getEmail());

                }
                Navigation.findNavController(OrderToComplete(v));

            } else {
                Snackbar.make(requireView(), "Önce ürün ekleyin", Snackbar.LENGTH_LONG).show();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFirebaseAuthListener();
    }

    private void setupFirebaseAuthListener() {
        authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {

                foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getItem().setBasketList(list));
                FoodOrderAdapter adapter = new FoodOrderAdapter(requireContext(),foodOrderViewModel,FoodBasketUtils.getItem().getBasketList(), this);

                binding.rvFoodOrder.setAdapter(adapter);



                FoodBasketUtils.getItem().getBasketLiveData().observe(getViewLifecycleOwner(), __ -> {
                    checkEmptyState();
                    adapter.setList(FoodBasketUtils.getItem().getBasketList());
                    Log.e("asd",FoodBasketUtils.getItem().getBasketList().toString());
                    binding.tvFoodOrderTotalPrice.setText(FoodBasketUtils.getItem().getBasketListTotalPrice());
                });
            } else {

                FoodBasketUtils.getItem().clearBasketList();
                foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getItem().setBasketList(list));

            }
        };
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkEmptyState();


    }

    public void checkEmptyState() {
        if (FoodBasketUtils.getItem().getBasketList().isEmpty()) {
            binding.rvFoodOrder.setVisibility(View.INVISIBLE);
            binding.tVEmptyState.setVisibility(View.VISIBLE);
            binding.iVEmptyState.setVisibility(View.VISIBLE);
        } else {
            binding.rvFoodOrder.setVisibility(View.VISIBLE);
            binding.tVEmptyState.setVisibility(View.INVISIBLE);
            binding.iVEmptyState.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (authStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
        binding = null;
    }
}