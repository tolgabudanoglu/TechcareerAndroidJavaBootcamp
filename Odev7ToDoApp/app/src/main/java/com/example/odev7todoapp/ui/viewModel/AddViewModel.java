package com.example.odev7todoapp.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.data.repo.ToDoDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddViewModel extends ViewModel {

    public ToDoDaoRepository trepo;

    @Inject
    public AddViewModel(ToDoDaoRepository trepo) {
        this.trepo = trepo;
    }

    public void addData(String toDo_name){
        trepo.add(toDo_name);

    }
}
