package com.example.lezzetkapida.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.SignInFragment;
import com.example.lezzetkapida.databinding.FragmentHomeBinding;
import com.example.lezzetkapida.ui.home.adapter.HomeAdapter;
import com.example.lezzetkapida.ui.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.ui.viewModel.HomeViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.example.lezzetkapida.utils.Listeners;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        Log.e("user",firebaseUser.getEmail().toString());

        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        binding.floatingActionButton.setOnClickListener(v -> {
            Listeners.HomeToOrderScreen(v);
        });



        binding.HomeToolbar.addMenuProvider(new MenuProvider() {
        @Override
        public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.home_fragment_menu,menu);

            MenuItem menuItem = menu.findItem(R.id.menuSearch);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            SearchView searchView = new SearchView(getActivity());
            searchView.setOnQueryTextListener(HomeFragment.this);
            menuItem.setActionView(searchView);



        }
        @Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                    if (itemId == R.id.menuSignOut) {
                        FirebaseAuth.getInstance().signOut();
                        Log.e("user",firebaseUser.getEmail().toString());

                        NavHostFragment.findNavController(HomeFragment.this)
                                .navigate(R.id.action_homeFragment_to_signInFragment);
                    };

                return true;
            }}, getViewLifecycleOwner(), Lifecycle.State.RESUMED);







        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshData();


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.foodSearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.foodSearch(newText);
        return true;
    }

    private void refreshData() {
        viewModel.foodList.observe(getViewLifecycleOwner(), newFoodList -> {
            if (binding.recyclerView.getAdapter() == null) {
                HomeAdapter adapter = new HomeAdapter(newFoodList, requireContext(), viewModel);
                binding.recyclerView.setAdapter(adapter);
            } else {
                ((HomeAdapter) binding.recyclerView.getAdapter()).updateFoodList(newFoodList);
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }


}