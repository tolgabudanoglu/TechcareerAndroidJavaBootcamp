package com.example.odev7todoapp.ui.fragment.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.FragmentListBinding;
import com.example.odev7todoapp.ui.fragment.list.adapter.ListAdapter;
import com.example.odev7todoapp.ui.viewModel.ListViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListFragment extends Fragment {

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








        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
    }

}