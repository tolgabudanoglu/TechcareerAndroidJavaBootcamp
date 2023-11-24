package com.example.odev7todoapp.ui.fragment.update;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.FragmentUpdateBinding;
import com.example.odev7todoapp.ui.viewModel.UpdateViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;
    private UpdateViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);



        UpdateFragmentArgs bundle = UpdateFragmentArgs.fromBundle(getArguments());
        ToDo toDo = bundle.getToDo();

        binding.etDescriptionUpdate.setText(toDo.getName());

        binding.btnUpdate.setOnClickListener(v -> {

            String toDoName = binding.etDescriptionUpdate.getText().toString();

            viewModel.updateData(toDo.getId(),toDoName);
            Navigation.findNavController(v).navigate(R.id.action_updateFragment_to_listFragment);
        });



        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
    }
}