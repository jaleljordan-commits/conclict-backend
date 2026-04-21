package com.conflicttracker.service;

import com.conflicttracker.dto.EventCreateDTO;
import com.conflicttracker.dto.EventDTO;
import com.conflicttracker.exception.ResourceNotFoundException;
import com.conflicttracker.model.Conflict;
import com.conflicttracker.model.Event;
import com.conflicttracker.repository.ConflictRepository;
import com.conflicttracker.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;

    public EventService(EventRepository eventRepository, ConflictRepository conflictRepository) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
    }

    @Transactional(readOnly = true)
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventDTO> findByConflictId(Long conflictId) {
        return eventRepository.findByConflictId(conflictId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EventDTO findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        return convertToDTO(event);
    }

    public EventDTO create(EventCreateDTO eventCreateDTO) {
        Event event = new Event();
        event.setEventDate(eventCreateDTO.getEventDate());
        event.setLocation(eventCreateDTO.getLocation());
        event.setDescription(eventCreateDTO.getDescription());

        Conflict conflict = conflictRepository.findById(eventCreateDTO.getConflictId())
                .orElseThrow(() -> new ResourceNotFoundException("Conflict", "id", eventCreateDTO.getConflictId()));
        event.setConflict(conflict);

        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public EventDTO update(Long id, EventCreateDTO eventCreateDTO) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));

        existingEvent.setEventDate(eventCreateDTO.getEventDate());
        existingEvent.setLocation(eventCreateDTO.getLocation());
        existingEvent.setDescription(eventCreateDTO.getDescription());

        if (!existingEvent.getConflict().getId().equals(eventCreateDTO.getConflictId())) {
            Conflict conflict = conflictRepository.findById(eventCreateDTO.getConflictId())
                    .orElseThrow(() -> new ResourceNotFoundException("Conflict", "id", eventCreateDTO.getConflictId()));
            existingEvent.setConflict(conflict);
        }

        Event updatedEvent = eventRepository.save(existingEvent);
        return convertToDTO(updatedEvent);
    }

    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event", "id", id);
        }
        eventRepository.deleteById(id);
    }

    private EventDTO convertToDTO(Event event) {
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