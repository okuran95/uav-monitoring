/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.helper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author onurkuran
 */
public class Numbers {

    public static LocalTime calculateDuration(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return LocalTime.of((int) hours, (int) minutes, (int) seconds);
    }

    public static LocalTime calculateDuration(LocalDateTime start) {
        LocalDateTime now = LocalDateTime.now();
        return calculateDuration(start, now);
    }

}
