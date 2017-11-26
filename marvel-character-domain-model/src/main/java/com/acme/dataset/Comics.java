package com.acme.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Comics implements Serializable{

    private static final long serialVersionUID = 1234L;

    @SerializedName("available")
    public double available;

    @SerializedName("collectionURI")
    public String collectionURI;

    @SerializedName("items")
    public List<Items> items;

    @SerializedName("returned")
    public double returned;

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public double getReturned() {
        return returned;
    }

    public void setReturned(double returned) {
        this.returned = returned;
    }
}