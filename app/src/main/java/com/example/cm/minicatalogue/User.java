package com.example.cm.minicatalogue;


import android.graphics.Bitmap;
import android.widget.ImageView;

public class User {
    String name;
    String status;
    ImageView image;
    Bitmap bm;
    int picture;

    public User(String name, String status, ImageView image, Bitmap bm) {
        this.name = name;
        this.status = status;
        this.image = image;
        this.bm = bm;
    }

    public User(String name, String status, Bitmap bm) {
        this.name = name;
        this.status = status;
        this.bm = bm;
    }

    public User(String name, String status, int picture) {
        this.name = name;
        this.status = status;
        this.picture = picture;
    }
    public User(String name, String status, ImageView image){
        this.name = name;
        this.status = status;
        this.image = image ;
    }

    public Bitmap getBm() {
        return bm;
    }

    public ImageView getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getPicture() {
        return picture;
    }
}
