package com.example.lezzetkapida.ui.foodOrder;

import static com.example.lezzetkapida.utils.Listeners.OrderToComplete;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.databinding.FragmentFoodOrderBinding;
import com.example.lezzetkapida.ui.foodOrder.adapter.FoodOrderAdapter;
import com.example.lezzetkapida.ui.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.example.lezzetkapida.utils.Listeners;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodOrderFragment extends Fragment {

    private FragmentFoodOrderBinding binding;
    private FoodOrderViewModel foodOrderViewModel;

    private FoodBasketUtils foodBasketUtils;
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








        foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getInstance().setBasketList(list));

        foodOrderViewModel.getDeletedInBasketLiveData().observe(getViewLifecycleOwner(), isDeleted -> {
            if (isDeleted) {
                foodOrderViewModel.getAllBasketFood();
            } else {
                Snackbar.make(requireView(), "Can't delete item!", Snackbar.LENGTH_SHORT).show();
            }
        });


        binding.btnFoodOrderConfirm.setOnClickListener(v -> {
            if (FoodBasketUtils.getInstance().getBasketList().size() > 0) {
                for (FoodBasket basket : FoodBasketUtils.getInstance().getBasketList()) {
                    foodOrderViewModel.deleteAllFood(basket.getBasketId(), firebaseUser.getEmail());

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFirebaseAuthListener();
    }

    private void setupFirebaseAuthListener() {
        authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // Kullanıcı oturum açtı veya oturumu açık
                // Burada gerekli güncelleme işlemlerini yapabilirsiniz
                foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getInstance().setBasketList(list));
                FoodOrderAdapter adapter = new FoodOrderAdapter(requireContext(),foodOrderViewModel,FoodBasketUtils.getInstance().getBasketList(), this);
                adapter.setList(FoodBasketUtils.getInstance().getBasketList());
                binding.rvFoodOrder.setAdapter(adapter);
                Toast.makeText(requireContext(), "User logged in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                //foodOrderViewModel.getAllBasketFood(); // Verileri güncelle
                binding.tvFoodOrderTotalPrice.setText(FoodBasketUtils.getInstance().getBasketListTotalPrice());
                FoodBasketUtils.getInstance().getBasketLiveData().observe(getViewLifecycleOwner(), __ -> {
                    checkEmptyState();
                    adapter.setList(FoodBasketUtils.getInstance().getBasketList());
                    Log.e("asd",FoodBasketUtils.getInstance().getBasketList().toString());
                    binding.tvFoodOrderTotalPrice.setText(FoodBasketUtils.getInstance().getBasketListTotalPrice());
                });
            } else {
                // Kullanıcı oturumu kapattı veya oturum kapalı
                // Burada gerekli işlemleri yapabilirsiniz
                FoodBasketUtils.getInstance().clearBasketList();
                foodOrderViewModel.getBasketListLiveData().observe(getViewLifecycleOwner(), list -> FoodBasketUtils.getInstance().setBasketList(list));
                Toast.makeText(requireContext(), "User logged out", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Firebase Auth dinleyicisini kaldır
        if (authStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
        binding = null;
    }
}