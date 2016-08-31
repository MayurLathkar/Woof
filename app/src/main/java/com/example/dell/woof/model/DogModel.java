package com.example.dell.woof.model;

import android.graphics.Bitmap;

/**
 * Created by praful on 15/8/16.
 */
public class DogModel {
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap bitmap;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;

    public DogModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public DogModel(String url) {
        this.url = url;
    }
}
