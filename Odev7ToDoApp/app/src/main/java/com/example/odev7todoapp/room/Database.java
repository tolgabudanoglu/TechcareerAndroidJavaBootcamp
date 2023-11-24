package com.example.odev7todoapp.room;


import androidx.room.RoomDatabase;

import com.example.odev7todoapp.data.entity.ToDo;

@androidx.room.Database(entities = {ToDo.class},version =1)
public abstract class Database extends RoomDatabase {
    public abstract ToDoDao getToDoDao();
}
