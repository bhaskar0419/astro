package com.pranavaeet.astro.controller;


import com.pranavaeet.astro.entity.InputParameters;
import com.pranavaeet.astro.service.CalculationService;
import com.pranavaeet.astro.service.OutputStructure;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/calculatePosition")
public class PositionCalculatorController {
    @Autowired
    CalculationService calculationService;

    @RequestMapping("parameterInput")
    public String displayInputForm(Model model){
        model.addAttribute("inputparameters",new InputParameters());
        return "planetcalculator";
    }

    @PostMapping("calculate")
    public String positionCalculation(@RequestParam("lat") double lat, @RequestParam("lon") double lon, @RequestParam("datetime") String datetime, Model model){

        List<OutputStructure> planetslocations=calculationService.LocationCalculator(lat,lon,datetime);
        model.addAttribute("planetslocations",planetslocations);
        System.out.println("lat"+lat);
        System.out.println("lon"+lon);
        System.out.println("datetime"+datetime);
        for(OutputStructure outputStructure:planetslocations){
            System.out.println(outputStructure.planetname);
            System.out.println(outputStructure.r);
            System.out.println(outputStructure.phi);
            System.out.println(outputStructure.elevation);
            System.out.println(outputStructure.azimuth);
        }
        return "planetpositions";
    }
}
