package com.conflicttracker.controller;

import com.conflicttracker.dto.EventCreateDTO;
import com.conflicttracker.dto.EventDTO;
import com.conflicttracker.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.findAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        EventDTO event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/conflict/{conflictId}")
    public ResponseEntity<List<EventDTO>> getEventsByConflictId(@PathVariable Long conflictId) {
        List<EventDTO> events = eventService.findByConflictId(conflictId);
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        EventDTO createdEvent = eventService.create(eventCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(
            @PathVariable Long id,
            @RequestBody EventCreateDTO eventCreateDTO) {

        EventDTO updatedEvent = eventService.update(id, eventCreateDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}