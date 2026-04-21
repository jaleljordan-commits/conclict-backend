package com.conflicttracker.controller;

import com.conflicttracker.dto.ConflictDTO;
import com.conflicttracker.dto.CountryDTO;
import com.conflicttracker.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        List<CountryDTO> countries = countryService.findAll();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable String code) {
        CountryDTO country = countryService.findByCode(code);
        return ResponseEntity.ok(country);
    }

    @GetMapping("/{code}/conflicts")
    public ResponseEntity<List<ConflictDTO>> getConflictsByCountryCode(@PathVariable String code) {
        List<ConflictDTO> conflicts = countryService.findConflictsByCountryCode(code);
        return ResponseEntity.ok(conflicts);
    }
}