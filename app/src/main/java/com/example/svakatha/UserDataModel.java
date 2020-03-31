package com.example.svakatha;

import android.graphics.Bitmap;
import android.net.Uri;

public class UserDataModel {
    String name, totalLikes;
    int photo;
    String url;
    String imageCode;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap bitmap;



    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(String totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {  this.photo = photo;
    }
}
