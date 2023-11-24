package com.example.odev7todoapp.di;

import android.content.Context;

import androidx.room.Room;

import com.example.odev7todoapp.data.repo.ToDoDaoRepository;
import com.example.odev7todoapp.room.Database;
import com.example.odev7todoapp.room.ToDoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public ToDoDaoRepository provideToDoDaoRepository(ToDoDao tDao){
        return new ToDoDaoRepository(tDao);
    }
    @Provides
    @Singleton
    public ToDoDao provideToDao(@ApplicationContext Context context){
        Database database = Room.databaseBuilder(context,Database.class,"toDoDatabase.sqlite")
                .createFromAsset("toDoDatabase.sqlite").build();
        return database.getToDoDao();
    }
}
