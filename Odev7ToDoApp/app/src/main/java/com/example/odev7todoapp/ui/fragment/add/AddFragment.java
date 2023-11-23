package com.example.odev7todoapp.ui.fragment.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.databinding.FragmentAddBinding;


public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentAddBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}