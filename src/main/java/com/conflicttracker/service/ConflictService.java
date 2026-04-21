package com.conflicttracker.service;

import com.conflicttracker.dto.ConflictCreateDTO;
import com.conflicttracker.dto.ConflictDTO;
import com.conflicttracker.dto.EventDTO;
import com.conflicttracker.exception.ResourceNotFoundException;
import com.conflicttracker.model.Conflict;
import com.conflicttracker.model.ConflictStatus;
import com.conflicttracker.model.Country;
import com.conflicttracker.model.Faction;
import com.conflicttracker.model.Event;
import com.conflicttracker.repository.ConflictRepository;
import com.conflicttracker.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConflictService {

    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public ConflictService(ConflictRepository conflictRepository, CountryRepository countryRepository) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<ConflictDTO> findAll() {
        return conflictRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConflictDTO> findByStatus(ConflictStatus status) {
        return conflictRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConflictDTO findById(Long id) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conflict", "id", id));
        return convertToDTO(conflict);
    }

    public ConflictDTO create(ConflictCreateDTO conflictCreateDTO) {
        Conflict conflict = convertToEntity(conflictCreateDTO);

        if (conflictCreateDTO.getCountryIds() != null && !conflictCreateDTO.getCountryIds().isEmpty()) {
            List<Country> countries = countryRepository.findAllById(conflictCreateDTO.getCountryIds());
            conflict.setCountries(new java.util.HashSet<>(countries));
        }

        Conflict savedConflict = conflictRepository.save(conflict);
        return convertToDTO(savedConflict);
    }

    public ConflictDTO update(Long id, ConflictCreateDTO conflictCreateDTO) {
        Conflict existingConflict = conflictRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conflict", "id", id));

        existingConflict.setName(conflictCreateDTO.getName());
        existingConflict.setStartDate(conflictCreateDTO.getStartDate());
        existingConflict.setStatus(conflictCreateDTO.getStatus());
        existingConflict.setDescription(conflictCreateDTO.getDescription());

        if (conflictCreateDTO.getCountryIds() != null) {
            List<Country> countries = countryRepository.findAllById(conflictCreateDTO.getCountryIds());
            existingConflict.setCountries(new java.util.HashSet<>(countries));
        }

        Conflict updatedConflict = conflictRepository.save(existingConflict);
        return convertToDTO(updatedConflict);
    }

    public void delete(Long id) {
        if (!conflictRepository.existsById(id)) {
            throw new ResourceNotFoundException("Conflict", "id", id);
        }
        conflictRepository.deleteById(id);
    }

    private ConflictDTO convertToDTO(Conflict conflict) {
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

        if (conflict.getFactions() != null) {
            dto.setFactionNames(conflict.getFactions().stream()
                    .map(Faction::getName)
                    .collect(Collectors.toSet()));
        }

        if (conflict.getEvents() != null) {
            dto.setEvents(conflict.getEvents().stream()
                    .map(this::convertEventToDTO)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    private Conflict convertToEntity(ConflictCreateDTO dto) {
        Conflict conflict = new Conflict();
        conflict.setName(dto.getName());
        conflict.setStartDate(dto.getStartDate());
        conflict.setStatus(dto.getStatus());
        conflict.setDescription(dto.getDescription());
        return conflict;
    }

    private EventDTO convertEventToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setEventDate(event.getEventDate());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());
        dto.setConflictId(event.getConflict().getId());
        dto.setConflictName(event.getConflict().getName());
        return dto;
    }
}