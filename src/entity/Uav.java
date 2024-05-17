package entity;

import core.helper.Numbers;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Uav {

    private int id;
    private String code;
    private Double batteryPercentage;
    private Position geoPosition;
    private Double speed;
    private LocalDateTime flightStartedTime;
    private LocalDateTime flightFinishedTime;

    public Uav(int id, String code, Double batteryPercentage, Position geoPosition, Double speed, LocalDateTime flightStartedTime, LocalDateTime flightFinishedDatetime) {
        this.id = id;
        this.code = code;
        this.batteryPercentage = batteryPercentage;
        this.geoPosition = geoPosition;
        this.speed = speed;
        this.flightStartedTime = flightStartedTime;
        this.flightFinishedTime = flightFinishedDatetime;
    }

    public Uav() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(Double battaryPercentage) {
        this.batteryPercentage = battaryPercentage;
    }

    public Position getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(Position geoPosition) {
        this.geoPosition = geoPosition;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public LocalDateTime getFlightStartedTime() {
        return flightStartedTime;
    }

    public void setFlightStartedTime(LocalDateTime flightStartedTime) {
        this.flightStartedTime = flightStartedTime;
    }

    public LocalDateTime getFlightFinishedTime() {
        return flightFinishedTime;
    }

    public void setFlightFinishedTime(LocalDateTime flightFinishedTime) {
        this.flightFinishedTime = flightFinishedTime;
    }

    public LocalTime getDuration() {
        if (flightFinishedTime != null) {
            return Numbers.calculateDuration(flightStartedTime, flightFinishedTime);
        } else {
            return Numbers.calculateDuration(flightStartedTime);
        }

    }

    public void create(String code, Double battaryPercentage, Position geoPosition, Double speed, LocalDateTime flightStartedTime) {
        this.code = code;
        this.batteryPercentage = battaryPercentage;
        this.geoPosition = geoPosition;
        this.speed = speed;
        this.flightStartedTime = flightStartedTime;
    }

    public Boolean getFlightStatus() {
        return this.flightFinishedTime == null && this.getBatteryPercentage() > 0 && getDuration().getHour() < 5;
    }

    public Boolean getWarningStatus() {
        return this.batteryPercentage < 25;
    }

    public void update(String code, double validatedBattery, double validatedSpeed) {
        this.code = code;
        this.batteryPercentage = validatedBattery;
        this.speed = validatedSpeed;
    }

}
