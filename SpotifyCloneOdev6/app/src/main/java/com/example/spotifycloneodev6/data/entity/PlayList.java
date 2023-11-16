package com.example.spotifycloneodev6.data.entity;

import java.io.Serializable;

public class PlayList implements Serializable {

    private int playListId;
    private String playListName;
    private String playListImage;

    public PlayList() {
    }

    public PlayList(int listId, String listName, String listImage) {
        this.playListId = listId;
        this.playListName = listName;
        this.playListImage = listImage;
    }

    public int getPlayListId() {
        return playListId;
    }

    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public String getPlayListImage() {
        return playListImage;
    }

    public void setPlayListImage(String playListImage) {
        this.playListImage = playListImage;
    }
}

