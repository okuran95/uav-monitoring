/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.helper;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author onurkuran
 */
public class ValidationHelper {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isValidDecimalFormat(String str, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        try {
            decimalFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidLatitude(double latitude) {
        return latitude >= -90.0 && latitude <= 90.0;
    }

    public static boolean isValidLongitude(double longitude) {
        return longitude >= -180.0 && longitude <= 180.0;
    }
    
        public static boolean isValidAltitude(double altitude) {
        return altitude >= 0 && altitude <= 100.0;
    }

}
