package com.dali.dalishop.bean;

public class HomeCampaginBean {

    private String title;
    private int bigImg;
    private int smallImgTop;
    private int smallImgBottom;

    public HomeCampaginBean(String title, int bigImg, int smallImgTop, int smallImgBottom) {
        this.title = title;
        this.bigImg = bigImg;
        this.smallImgTop = smallImgTop;
        this.smallImgBottom = smallImgBottom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBigImg() {
        return bigImg;
    }

    public void setBigImg(int bigImg) {
        this.bigImg = bigImg;
    }

    public int getSmallImgTop() {
        return smallImgTop;
    }

    public void setSmallImgTop(int smallImgTop) {
        this.smallImgTop = smallImgTop;
    }

    public int getSmallImgBottom() {
        return smallImgBottom;
    }

    public void setSmallImgBottom(int smallImgBottom) {
        this.smallImgBottom = smallImgBottom;
    }
}
