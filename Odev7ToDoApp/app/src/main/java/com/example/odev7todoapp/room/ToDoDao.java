package com.example.odev7todoapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.odev7todoapp.data.entity.ToDo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ToDoDao {

    @Query("SELECT * FROM toDos")
    Single<List<ToDo>> getToDo();

    @Insert
    Completable add(ToDo toDo);

    @Update
    Completable update(ToDo toDo);

    @Delete
    Completable delete(ToDo toDo);

    @Query("DELETE FROM toDos")
    Completable deleteAll();


    @Query("SELECT * FROM toDos WHERE name like '%' || :search ||   '%'")
    Single<List<ToDo>> search(String search);
}
