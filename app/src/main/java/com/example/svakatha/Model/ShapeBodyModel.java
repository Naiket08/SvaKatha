package com.example.svakatha.Model;

import android.net.Uri;

public class ShapeBodyModel {

    Uri downloadlink;

    public ShapeBodyModel(Uri downloadlink) {
        this.downloadlink = downloadlink;
    }

    public Uri getDownloadlink() {
        return downloadlink;
    }

    public void setDownloadlink(Uri downloadlink) {
        this.downloadlink = downloadlink;
    }
}
