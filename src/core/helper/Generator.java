/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.helper;

import entity.Position;
import entity.Uav;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author onurkuran
 */
public class Generator {

    public static Position coordinateGenerate() {
        Random random = new Random();
        double latitude = -90 + (90 - (-90)) * random.nextDouble();
        double longitude = -180 + (180 - (-180)) * random.nextDouble();
        double altitude = 100 * random.nextDouble();

        double formattedAltitude = BigDecimal.valueOf(altitude).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double formattedLongitude = BigDecimal.valueOf(longitude).setScale(7, RoundingMode.HALF_UP).doubleValue();
        double formattedLatitude = BigDecimal.valueOf(latitude).setScale(7, RoundingMode.HALF_UP).doubleValue();

        Position position = new Position();
        position.create(formattedLatitude, formattedLongitude, formattedAltitude);
        return position;
    }

    public static double doubleGenerate() {
        Random random = new Random();
        int beforeDecimal = random.nextInt(101);
        int afterDecimal = random.nextInt(10);
        return beforeDecimal + (double) afterDecimal / 10;
    }

    public static double doubleGenerate(double min, double max, int decimalPlaces) {
        Random random = new Random();
        double scaled = random.nextDouble() * (max - min) + min;

        double scale = Math.pow(10, decimalPlaces);
        return Math.round(scaled * scale) / scale;
    }

    public static String stringGenerate() {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomAscii = random.nextInt(75) + 48;
            if (randomAscii >= 58 && randomAscii <= 64) {
                randomAscii += 7;
            } else if (randomAscii >= 91 && randomAscii <= 96) {
                randomAscii += 6;
            }
            sb.append((char) randomAscii);
        }
        String randomString = sb.toString();

        return randomString;
    }

    public static int generateUniqueId(ArrayList<Uav> uavs) {
        Set<Integer> idSet = new HashSet<>();
        for (Uav obj : uavs) {
            idSet.add(obj.getId());
        }
        
        Random random = new Random();
        int newId;
        do {
            newId = random.nextInt(2000); // Örnek bir benzersiz kimlik üret
        } while (idSet.contains(newId));
        return newId;
    }
}
