package entity;

import core.helper.Numbers;
import java.time.LocalDateTime;
import java.time.LocalTime;


/*
·         uavId: [1-2000] integer - unique – İHA Id (otomatik atama yapılabilir)
·         uavCode: 32 Karakter String – İHA Görünen Adı
·         uavBattaryPercentage: 0-100% – İHA Batarya Doluluk Oranı – Girilen değer her dakika ortalama olarak 0.2% azaltılacaktır
·         uavGeoPosition: Lat/Long/Alt – İHA Kalkış Pozisyonu
·         uavSpeed: 0.1 – 5.0 m/sec – İHA Yatay Hız Bilgisi – Verilen aralıkta herhangi bir değer alabilir
·         uavTotalFlightTime: 0 - 5h – hh:mm:sec - İHA Toplam Uçuş Süresi – El ile giriş kabul edilmeyecektir.
Kayıt oluşturulduğu andan itibaren saniye hassasiyetinde otomatik arttırılacaktır.
 */
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

    /*
    public Double getSpeed() {
        return speed;
    }
     */
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
