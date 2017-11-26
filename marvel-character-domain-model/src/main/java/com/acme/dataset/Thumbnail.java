package com.acme.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Thumbnail implements Serializable{

    private static final long serialVersionUID = 1234L;

    @SerializedName("path")
    public String path;

    @SerializedName("extension")
    public String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}