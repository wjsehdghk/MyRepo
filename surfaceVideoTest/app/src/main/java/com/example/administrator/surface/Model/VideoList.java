package com.example.administrator.surface.Model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016-09-13.
 */
public class VideoList {


    Bitmap ThumbImage;

    public VideoList(Bitmap thumbImage) {
        ThumbImage = thumbImage;
    }


    public Bitmap getThumbImage() {
        return ThumbImage;
    }

    public void setThumbImage(Bitmap thumbImage) {
        ThumbImage = thumbImage;
    }
}
