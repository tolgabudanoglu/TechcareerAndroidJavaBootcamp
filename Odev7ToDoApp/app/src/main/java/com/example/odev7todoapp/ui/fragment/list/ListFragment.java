package com.example.odev7todoapp.ui.fragment.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.FragmentListBinding;
import com.example.odev7todoapp.ui.fragment.list.adapter.ListAdapter;

import java.util.ArrayList;


public class ListFragment extends Fragment {

   private FragmentListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater, container, false);


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        ArrayList<ToDo> arrayList = new ArrayList<>();

        ToDo td1 = new ToDo(1,"adfadf");
        ToDo td2 = new ToDo(2,"afdf");
        ToDo td3 = new ToDo(3,"adfdafadfadf");
        ToDo td4 = new ToDo(4,"dag");
        ToDo td5 = new ToDo(5,"gagsfasfg");

        arrayList.add(td1);
        arrayList.add(td2);
        arrayList.add(td3);
        arrayList.add(td4);
        arrayList.add(td5);

        ListAdapter listAdapter = new ListAdapter(arrayList,requireContext());
        binding.recyclerView.setAdapter(listAdapter);


        binding.floatingActionButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_listFragment_to_addFragment);
        });


        return binding.getRoot();
    }
}