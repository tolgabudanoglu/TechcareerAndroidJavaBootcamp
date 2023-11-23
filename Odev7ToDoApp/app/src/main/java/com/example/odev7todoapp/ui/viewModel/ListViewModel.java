package com.example.odev7todoapp.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.odev7todoapp.data.entity.ToDo;

import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<ToDo>> toDoList;

    public void getAllData(){

    }

    public void search(String search){

    }

    public void deleteItem(int toDo_id){

    }

    public void deleteAll(){

    }
}
