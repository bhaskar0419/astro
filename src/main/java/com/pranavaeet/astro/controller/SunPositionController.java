package com.pranavaeet.astro.controller;


import com.pranavaeet.astro.service.SunPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/Calculation")
public class SunPositionController {
    @Autowired
    private SunPositionService sunPositionService;

    @GetMapping("/sun")
    public String getSunPosition(@RequestParam double latitude,@RequestParam double longitude,@RequestParam String datetime){
        try {
            return SunPositionService.calculateSunPosition(latitude,longitude,datetime);
        }
        catch (Exception e){
            return "Error :"+ e.getMessage();
        }
    }

}
