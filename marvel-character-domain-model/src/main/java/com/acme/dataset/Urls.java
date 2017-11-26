package com.acme.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Urls implements Serializable{

    private static final long serialVersionUID = 1234L;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}