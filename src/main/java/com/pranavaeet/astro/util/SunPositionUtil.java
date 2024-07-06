package com.pranavaeet.astro.util;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SunPositionUtil {

    public static double[] calculateSunPosition(double latitude, double longitude, LocalDateTime dateTime) {
        // Convert the date to Julian Day
        double julianDay = toJulianDay(dateTime);

        // Calculate the number of days since J2000.0
        double n = julianDay - 2451545.0;

        // Mean longitude of the Sun
        double L = (280.460 + 0.9856474 * n) % 360;
        if (L < 0) L += 360;

        // Mean anomaly of the Sun
        double g = (357.528 + 0.9856003 * n) % 360;
        if (g < 0) g += 360;
        g = Math.toRadians(g);

        // Ecliptic longitude of the Sun
        double lambda = L + 1.915 * Math.sin(g) + 0.020 * Math.sin(2 * g);
        lambda = Math.toRadians(lambda);

        // Obliquity of the ecliptic
        double epsilon = Math.toRadians(23.439 - 0.0000004 * n);

        // Right ascension of the Sun
        double alpha = Math.atan2(Math.cos(epsilon) * Math.sin(lambda), Math.cos(lambda));

        // Declination of the Sun
        double delta = Math.asin(Math.sin(epsilon) * Math.sin(lambda));

        // Local sidereal time
        double LST = (280.46061837 + 360.98564736629 * n + longitude) % 360;
        if (LST < 0) LST += 360;
        LST = Math.toRadians(LST);

        // Hour angle
        double H = LST - alpha;
        if (H < -Math.PI) H += 2 * Math.PI;
        if (H > Math.PI) H -= 2 * Math.PI;

        // Elevation angle
        double elevation = Math.asin(Math.sin(latitude) * Math.sin(delta) + Math.cos(latitude) * Math.cos(delta) * Math.cos(H));

        // Azimuth angle
        double azimuth = Math.atan2(-Math.sin(H), Math.tan(delta) * Math.cos(latitude) - Math.sin(latitude) * Math.cos(H));
        if (azimuth < 0) azimuth += 2 * Math.PI;

        // Convert to degrees
        azimuth = Math.toDegrees(azimuth);
        elevation = Math.toDegrees(elevation);

        return new double[]{azimuth, elevation};
    }

    private static double toJulianDay(LocalDateTime dateTime) {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();

        if (month <= 2) {
            year -= 1;
            month += 12;
        }

        int A = year / 100;
        int B = 2 - A + A / 4;

        double jd = (int) (365.25 * (year + 4716)) + (int) (30.6001 * (month + 1)) + day + B - 1524.5;
        jd += (hour + minute / 60.0 + second / 3600.0) / 24.0;

        return jd;
    }
}
