package com.example.svakatha;

public class UserDataModel {
    String name, totalLikes;
    int photo;
    String url;
    String imageCode;

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
