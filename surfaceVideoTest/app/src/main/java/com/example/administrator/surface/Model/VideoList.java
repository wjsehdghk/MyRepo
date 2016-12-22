package com.example.administrator.surface.Model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016-09-13.
 */
public class VideoList {
    Bitmap ThumbImage;
    String URL;
    public final long id;
    public VideoList(Bitmap thumbImage, String url,long id) {
        ThumbImage = thumbImage;
        URL = url;
        this.id=id;
    }
    public String getURL() {
        return URL;
    }
    public Bitmap getThumbImage() {
        return ThumbImage;
    }
    public void setThumbImage(Bitmap thumbImage) {
        ThumbImage = thumbImage;
    }
}
