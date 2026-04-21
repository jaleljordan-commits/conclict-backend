package com.conflicttracker.service;

import com.conflicttracker.dto.FactionCreateDTO;
import com.conflicttracker.dto.FactionDTO;
import com.conflicttracker.exception.ResourceNotFoundException;
import com.conflicttracker.model.Conflict;
import com.conflicttracker.model.Country;
import com.conflicttracker.model.Faction;
import com.conflicttracker.repository.ConflictRepository;
import com.conflicttracker.repository.CountryRepository;
import com.conflicttracker.repository.FactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FactionService {

    private final FactionRepository factionRepository;
    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public FactionService(FactionRepository factionRepository,
                          ConflictRepository conflictRepository,
                          CountryRepository countryRepository) {
        this.factionRepository = factionRepository;
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<FactionDTO> findAll() {
        return factionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FactionDTO> findByConflictId(Long conflictId) {
        return factionRepository.findByConflictId(conflictId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FactionDTO findById(Long id) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faction", "id", id));
        return convertToDTO(faction);
    }

    public FactionDTO create(FactionCreateDTO factionCreateDTO) {
        Faction faction = new Faction();
        faction.setName(factionCreateDTO.getName());

        Conflict conflict = conflictRepository.findById(factionCreateDTO.getConflictId())
                .orElseThrow(() -> new ResourceNotFoundException("Conflict", "id", factionCreateDTO.getConflictId()));
        faction.setConflict(conflict);

        if (factionCreateDTO.getSupportingCountryIds() != null &&
                !factionCreateDTO.getSupportingCountryIds().isEmpty()) {
            List<Country> countries = countryRepository.findAllById(factionCreateDTO.getSupportingCountryIds());
            faction.setSupportingCountries(new java.util.HashSet<>(countries));
        }

        Faction savedFaction = factionRepository.save(faction);
        return convertToDTO(savedFaction);
    }

    public FactionDTO update(Long id, FactionCreateDTO factionCreateDTO) {
        Faction existingFaction = factionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faction", "id", id));

        existingFaction.setName(factionCreateDTO.getName());

        if (!existingFaction.getConflict().getId().equals(factionCreateDTO.getConflictId())) {
            Conflict conflict = conflictRepository.findById(factionCreateDTO.getConflictId())
                    .orElseThrow(() -> new ResourceNotFoundException("Conflict", "id", factionCreateDTO.getConflictId()));
            existingFaction.setConflict(conflict);
        }

        if (factionCreateDTO.getSupportingCountryIds() != null) {
            List<Country> countries = countryRepository.findAllById(factionCreateDTO.getSupportingCountryIds());
            existingFaction.setSupportingCountries(new java.util.HashSet<>(countries));
        }

        Faction updatedFaction = factionRepository.save(existingFaction);
        return convertToDTO(updatedFaction);
    }

    public void delete(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Faction", "id", id);
        }
        factionRepository.deleteById(id);
    }

    private FactionDTO convertToDTO(Faction faction) {
        FactionDTO dto = new FactionDTO();
        dto.setId(faction.getId());
        dto.setName(faction.getName());
        dto.setConflictId(faction.getConflict().getId());
        dto.setConflictName(faction.getConflict().getName());

        if (faction.getSupportingCountries() != null) {
            dto.setSupportingCountryNames(faction.getSupportingCountries().stream()
                    .map(Country::getName)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }
}