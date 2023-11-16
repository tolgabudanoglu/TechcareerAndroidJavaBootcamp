package com.example.spotifycloneodev6.data.entity;

import java.io.Serializable;

public class PlayListContiune implements Serializable {

    private int playListId;
    private String playListName;
    private String playListImage;

    private String playListExplanation;

    public PlayListContiune() {
    }

    public PlayListContiune(int playListId, String playListName, String playListImage, String playListExplanation) {
        this.playListId = playListId;
        this.playListName = playListName;
        this.playListImage = playListImage;
        this.playListExplanation = playListExplanation;
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

    public String getPlayListExplanation() {
        return playListExplanation;
    }

    public void setPlayListExplanation(String playListExplanation) {
        this.playListExplanation = playListExplanation;
    }
}
