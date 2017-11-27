package com.hillel.controllers;

import com.hillel.model.Country;
import com.hillel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/countries")
public class CountryRestController {

    @Autowired
    private CountryService countryService;


    @GetMapping(produces = "application/json")
    public List<Country> getAllCountries() {
        return countryService.getAll();
    }

}
