package com.example.muzejceoprojekat;

import org.json.JSONObject;

import java.io.Serializable;

public class Eksponat extends JSONObject implements Serializable {

    int objectID;
    String title;
    String country;
    String accessionYear;
    String imageURL;

    public Eksponat() {}

    public Eksponat(int objectID, String title, String accessionYear, String country, String imageURL) {
        this.objectID = objectID;
        this.title = title;
        this.country = country;
        this.accessionYear = accessionYear;
        this.imageURL = imageURL;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccessionYear() {
        return accessionYear;
    }

    public void setAccessionYear(String accessionYear) {
        this.accessionYear = accessionYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return objectID + "  " + title;
    }
}
