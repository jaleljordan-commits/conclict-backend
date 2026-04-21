package com.conflicttracker.dto;

import com.conflicttracker.model.ConflictStatus;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ConflictDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;
    private Set<String> countryNames = new HashSet<>();
    private Set<String> factionNames = new HashSet<>();
    private Set<EventDTO> events = new HashSet<>();

    public ConflictDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public ConflictStatus getStatus() { return status; }
    public void setStatus(ConflictStatus status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<String> getCountryNames() { return countryNames; }
    public void setCountryNames(Set<String> countryNames) { this.countryNames = countryNames; }
    public Set<String> getFactionNames() { return factionNames; }
    public void setFactionNames(Set<String> factionNames) { this.factionNames = factionNames; }
    public Set<EventDTO> getEvents() { return events; }
    public void setEvents(Set<EventDTO> events) { this.events = events; }
}