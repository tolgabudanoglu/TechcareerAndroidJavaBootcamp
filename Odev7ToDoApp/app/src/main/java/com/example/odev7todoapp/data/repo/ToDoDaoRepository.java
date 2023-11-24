package com.example.odev7todoapp.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.room.ToDoDao;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ToDoDaoRepository {

    public MutableLiveData<List<ToDo>> toDoList = new MutableLiveData<>();

    private ToDoDao tDao;

    public ToDoDaoRepository(ToDoDao tDao) {
        this.tDao = tDao;
    }

    public void add(String name){
        ToDo newToDo = new ToDo(0,name);
        tDao.add(newToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getToDo();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void update(int id,String name){
        ToDo updatedToDo = new ToDo(id,name);
        tDao.update(updatedToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getToDo();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void search(String search){
        tDao.search(search).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<ToDo> toDos) {
                        toDoList.setValue(toDos);

                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void deleteItem(int id){
        ToDo deletedToDo = new ToDo(id,"");
        tDao.delete(deletedToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                        getToDo();

                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void deleteAll(){
        tDao.deleteAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                        getToDo();

                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void getToDo() {

        tDao.getToDo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<ToDo> toDos) {
                        toDoList.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }


}
