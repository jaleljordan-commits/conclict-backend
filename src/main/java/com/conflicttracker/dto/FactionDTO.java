package com.conflicttracker.dto;

import java.util.HashSet;
import java.util.Set;

public class FactionDTO {
    private Long id;
    private String name;
    private Long conflictId;
    private String conflictName;
    private Set<String> supportingCountryNames = new HashSet<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
    public String getConflictName() { return conflictName; }
    public void setConflictName(String conflictName) { this.conflictName = conflictName; }
    public Set<String> getSupportingCountryNames() { return supportingCountryNames; }
    public void setSupportingCountryNames(Set<String> supportingCountryNames) { this.supportingCountryNames = supportingCountryNames; }
}