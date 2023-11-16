package com.example.spotifycloneodev6.data.entity;

public class Menu {
    private int menuId;
    private String menuText;

    public Menu() {
    }

    public Menu(int menuId, String menuText) {
        this.menuId = menuId;
        this.menuText = menuText;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }
}