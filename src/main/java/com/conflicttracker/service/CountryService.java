package com.conflicttracker.service;

import com.conflicttracker.dto.ConflictDTO;
import com.conflicttracker.dto.CountryDTO;
import com.conflicttracker.exception.ResourceNotFoundException;
import com.conflicttracker.model.Country;
import com.conflicttracker.model.Conflict;
import com.conflicttracker.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<CountryDTO> findAll() {
        return countryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CountryDTO findByCode(String code) {
        Country country = countryRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "code", code));
        return convertToDTO(country);
    }

    @Transactional(readOnly = true)
    public List<ConflictDTO> findConflictsByCountryCode(String code) {
        Country country = countryRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "code", code));

        return country.getConflicts().stream()
                .map(this::convertConflictToDTO)
                .collect(Collectors.toList());
    }

    private CountryDTO convertToDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setCode(country.getCode());
        return dto;
    }

    private ConflictDTO convertConflictToDTO(Conflict conflict) {
        ConflictDTO dto = new ConflictDTO();
        dto.setId(conflict.getId());
        dto.setName(conflict.getName());
        dto.setStartDate(conflict.getStartDate());
        dto.setStatus(conflict.getStatus());
        dto.setDescription(conflict.getDescription());

        if (conflict.getCountries() != null) {
            dto.setCountryNames(conflict.getCountries().stream()
                    .map(Country::getName)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }
}