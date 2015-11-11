package com.surefor.helloweather.entity;

/**
 * Created by ethan on 05/11/2015.
 */
public class Coordinate {
    Double longitude ;
    Double latitude ;

    public Coordinate() {

    }

    public Coordinate(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
