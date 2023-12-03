package com.example.lezzetkapida.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.databinding.FragmentHomeBinding;
import com.example.lezzetkapida.ui.home.adapter.HomeAdapter;
import com.example.lezzetkapida.ui.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.ui.viewModel.HomeViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);





        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        viewModel.foodList.observe(getViewLifecycleOwner(),foodList ->{
            HomeAdapter adapter = new HomeAdapter(foodList,requireContext(),viewModel);
            binding.recyclerView.setAdapter(adapter);
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
            updateRecyclerViewLayout();


        }@Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

            return false;
        }}, getViewLifecycleOwner(), Lifecycle.State.RESUMED);







        return binding.getRoot();
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
    private void updateRecyclerViewLayout() {
        binding.recyclerView.requestLayout();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }
}