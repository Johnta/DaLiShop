package com.dali.dalishop.bean;

public class Tab {

    private int title;
    private int image;
    private Class fragment;


    public Tab(int title, int image, Class fragment) {
        this.title = title;
        this.image = image;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
