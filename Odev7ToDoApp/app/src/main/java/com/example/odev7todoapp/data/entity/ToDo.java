package com.example.odev7todoapp.data.entity;

import java.io.Serializable;

public class ToDo implements Serializable {

    private int id;
    private String name;

    public ToDo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
