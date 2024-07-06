package com.pranavaeet.astro.service;

import com.pranavaeet.astro.util.SunPositionUtil;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class SunPositionService {
    public static String calculateSunPosition(double latitude, double longitude, String dateStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);

        double[] sunPosition = SunPositionUtil.calculateSunPosition(latitude, longitude, dateTime);

        double azimuth = sunPosition[0];
        double elevation = sunPosition[1];

        return String.format("Sun Azimuth: %.2f, Elevation: %.2f", azimuth, elevation);
    }

}
