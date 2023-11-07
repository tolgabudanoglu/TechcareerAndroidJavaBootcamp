package com.example.odev4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev4.databinding.FragmentSayfaABinding;
import com.example.odev4.databinding.FragmentSayfaBBinding;


public class SayfaBFragment extends Fragment {

    private FragmentSayfaBBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSayfaBBinding.inflate(inflater, container, false);

        binding.btnSayfaBtoY.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.sayfaBToSayfaY);
        });


        return binding.getRoot();
    }
}