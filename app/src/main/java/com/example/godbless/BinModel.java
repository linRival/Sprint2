package com.example.godbless;

public class BinModel {

    String bin_number, fill_level, latitude, longitude, surl, status;


    BinModel()
    {

    }
    public BinModel(String bin_number, String fill_level, String latitude, String longitude, String surl, String status) {
        this.bin_number = bin_number;
        this.fill_level = fill_level;
        this.latitude = latitude;
        this.longitude = longitude;
        this.surl=surl;
        this.status=status;
    }

    public String getBin_number() {
        return bin_number;
    }

    public void setBin_number(String bin_number) {
        this.bin_number = bin_number;
    }

    public String getFill_level() {
        return fill_level;
    }

    public void setFill_level(String fill_level) {
        this.fill_level = fill_level;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
