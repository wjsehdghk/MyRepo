package com.example.administrator.cardviewex.Model;

/**
 * Created by Administrator on 2016-08-10.
 */
public class ITem {
    int image;
    String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ITem(int image, String title) {

        this.image = image;
        this.title = title;
    }
}
