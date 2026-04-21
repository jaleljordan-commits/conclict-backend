package com.conflicttracker.dto;

import java.time.LocalDate;

public class EventDTO {
    private Long id;
    private LocalDate eventDate;
    private String location;
    private String description;
    private Long conflictId;
    private String conflictName;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
    public String getConflictName() { return conflictName; }
    public void setConflictName(String conflictName) { this.conflictName = conflictName; }
}