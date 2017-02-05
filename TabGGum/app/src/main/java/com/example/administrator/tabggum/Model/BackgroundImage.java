package com.example.administrator.tabggum.Model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016-08-17.
 */
public class BackgroundImage {
    int image;
    String title;


    public BackgroundImage(int image, String title) {
        this.image = image;
        this.title = title;
    }

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
}
