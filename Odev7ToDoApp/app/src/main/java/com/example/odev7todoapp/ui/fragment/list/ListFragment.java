package com.example.odev7todoapp.ui.fragment.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.FragmentListBinding;
import com.example.odev7todoapp.ui.fragment.list.adapter.ListAdapter;
import com.example.odev7todoapp.ui.viewModel.ListViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListFragment  extends Fragment implements SearchView.OnQueryTextListener  {

   private FragmentListBinding binding;
   private ListViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater, container, false);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));





        viewModel.toDoList.observe(getViewLifecycleOwner(),toDos ->{
            ListAdapter listAdapter = new ListAdapter(toDos,requireContext(),viewModel);
            binding.recyclerView.setAdapter(listAdapter);
        } );





        binding.floatingActionButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_listFragment_to_addFragment);
        });

        binding.toolbar.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.list_fragment_menu, menu);

                MenuItem item = menu.findItem(R.id.menuSearch);
                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                SearchView searchView = new SearchView(getActivity());
                searchView.setOnQueryTextListener(ListFragment.this);
                item.setActionView(searchView);

            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.menuDeleteAll) {
                    viewModel.deleteAll();

                }
                return true;
            }}, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        return binding.getRoot();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.search(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.search(newText);
        return true;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);




    }

}