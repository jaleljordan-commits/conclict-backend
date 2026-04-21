package com.conflicttracker.dto;

import java.util.HashSet;
import java.util.Set;

public class FactionCreateDTO {
    private String name;
    private Long conflictId;
    private Set<Long> supportingCountryIds = new HashSet<>();

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
    public Set<Long> getSupportingCountryIds() { return supportingCountryIds; }
    public void setSupportingCountryIds(Set<Long> supportingCountryIds) { this.supportingCountryIds = supportingCountryIds; }
}