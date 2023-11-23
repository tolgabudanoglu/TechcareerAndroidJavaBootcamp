package com.example.odev7todoapp.ui.fragment.update;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.FragmentUpdateBinding;


public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);



        UpdateFragmentArgs bundle = UpdateFragmentArgs.fromBundle(getArguments());
        ToDo toDo = bundle.getToDo();

        binding.etDescriptionUpdate.setText(toDo.getName());



        return binding.getRoot();
    }
}