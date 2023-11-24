package com.example.odev7todoapp.ui.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.odev7todoapp.data.repo.ToDoDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateViewModel extends ViewModel {

    public ToDoDaoRepository trepo;

    @Inject
    public UpdateViewModel(ToDoDaoRepository trepo) {
        this.trepo = trepo;
    }

    public void updateData(int toDo_id, String toDo_name){
        trepo.update(toDo_id,toDo_name);
    }

    public void deleteItem(int toDo_id){
        trepo.deleteItem(toDo_id);
    }
}
