package com.conflicttracker.dto;

import com.conflicttracker.model.ConflictStatus;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ConflictCreateDTO {
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;
    private Set<Long> countryIds = new HashSet<>();

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public ConflictStatus getStatus() { return status; }
    public void setStatus(ConflictStatus status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<Long> getCountryIds() { return countryIds; }
    public void setCountryIds(Set<Long> countryIds) { this.countryIds = countryIds; }
}