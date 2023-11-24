package com.example.odev7todoapp.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.data.repo.ToDoDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListViewModel extends ViewModel {

    public MutableLiveData<List<ToDo>> toDoList;
    public ToDoDaoRepository trepo;

    @Inject
    public ListViewModel( ToDoDaoRepository trepo) {
        this.trepo = trepo;
        getAllData();
        toDoList = trepo.toDoList;
    }

    public void getAllData(){
        trepo.getToDo();
    }

    public void search(String search){
        trepo.search(search);
    }

    public void deleteItem(int toDo_id){
        trepo.deleteItem(toDo_id);
    }

    public void deleteAll(){
        trepo.deleteAll();

    }
}
