package com.example.odev7todoapp.ui.fragment.add;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.databinding.FragmentAddBinding;
import com.example.odev7todoapp.ui.viewModel.AddViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private AddViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentAddBinding.inflate(inflater, container, false);


        binding.btnAdd.setOnClickListener(v -> {
            String toDo = binding.etDescription.getText().toString();

            viewModel.addData(toDo);
            Navigation.findNavController(v).navigate(R.id.action_addFragment_to_listFragment);
        });



        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
    }
}