/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


/**
 *
 * @author onurkuran
 */
public class Position {

    private int id;
    private Double latitude;
    private Double longitude;
    private Double altitude;

    public Position(int id, Double latitude, Double longitude, Double altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Position() {
    }

    public void create(Double latitude, Double longitude, Double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getAsString() {
        return this.latitude + "/" + this.longitude + "/" + this.altitude;
    }

    public void update(double validatedLatitude, double validatedLongitude, double validatedAltitude) {
     this.latitude = validatedLatitude;
     this.longitude = validatedLongitude;
     this.altitude = validatedAltitude;
    }

}
