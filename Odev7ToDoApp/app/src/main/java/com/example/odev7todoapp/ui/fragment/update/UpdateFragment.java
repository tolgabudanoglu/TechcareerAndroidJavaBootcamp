package com.example.odev7todoapp.ui.fragment.update;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.odev7todoapp.R;
import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.FragmentUpdateBinding;
import com.example.odev7todoapp.ui.fragment.list.ListFragment;
import com.example.odev7todoapp.ui.viewModel.UpdateViewModel;
import com.google.android.material.snackbar.Snackbar;

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

        binding.toolbar.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.update_fragment_menu, menu);
            }
            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.menuDelete) {
                    Snackbar.make(binding.toolbar,toDo.getName()+" silinsin mi",Snackbar.LENGTH_SHORT)
                            .setAction("evet",v1 -> {
                                viewModel.deleteItem(toDo.getId());
                                Navigation.findNavController(binding.toolbar).navigate(R.id.action_updateFragment_to_listFragment);
                            })
                            .show();
                }
                return true;
            }}, getViewLifecycleOwner(), Lifecycle.State.RESUMED);



        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
    }
}