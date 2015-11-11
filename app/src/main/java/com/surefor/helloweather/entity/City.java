package com.surefor.helloweather.entity;

/**
 * Created by ethan on 05/11/2015.
 */
public class City {
    Long id ;
    String name ;
    String country ;
    Coordinate coord = new Coordinate() ;

    public City() {

    }

    public City(Long id, String name, String country, Coordinate coord) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coord = coord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public double getLongitude() {
        return this.coord.getLongitude() ;
    }

    public void setLongitude(Double longitude) {
        coord.setLongitude(longitude) ;
    }

    public double getLatitude() {
        return this.coord.getLatitude() ;
    }

    public void setLatitude(Double latitude) {
        coord.setLatitude(latitude) ;
    }
}
