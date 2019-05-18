package com.example.capstone.Model;

import java.util.ArrayList;

public class Picture {

    private String fileName;
    private String uploadID;
    private ArrayList<String> person;
    private String uri;
    private String gpsProvider;
    private double longitude;
    private double latitude;
    private double altitude;

    public String getUploadID() {
        return uploadID;
    }

    public void setUploadID(String uploadID) {
        this.uploadID = uploadID;
    }

    public ArrayList<String> getPerson() {
        return person;
    }

    public void setPerson(ArrayList<String> person) {
        this.person = person;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getGpsProvider() {
        return gpsProvider;
    }

    public void setGpsProvider(String gpsProvider) {
        this.gpsProvider = gpsProvider;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
