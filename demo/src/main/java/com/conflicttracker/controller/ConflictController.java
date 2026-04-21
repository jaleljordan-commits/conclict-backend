package com.conflicttracker.controller;

import com.conflicttracker.dto.ConflictCreateDTO;
import com.conflicttracker.dto.ConflictDTO;
import com.conflicttracker.model.ConflictStatus;
import com.conflicttracker.service.ConflictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService conflictService;

    public ConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public ResponseEntity<List<ConflictDTO>> getAllConflicts(
            @RequestParam(required = false) ConflictStatus status) {

        List<ConflictDTO> conflicts;
        if (status != null) {
            conflicts = conflictService.findByStatus(status);
        } else {
            conflicts = conflictService.findAll();
        }

        return ResponseEntity.ok(conflicts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConflictDTO> getConflictById(@PathVariable Long id) {
        ConflictDTO conflict = conflictService.findById(id);
        return ResponseEntity.ok(conflict);
    }

    @PostMapping
    public ResponseEntity<ConflictDTO> createConflict(@RequestBody ConflictCreateDTO conflictCreateDTO) {
        ConflictDTO createdConflict = conflictService.create(conflictCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConflict);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConflictDTO> updateConflict(
            @PathVariable Long id,
            @RequestBody ConflictCreateDTO conflictCreateDTO) {

        ConflictDTO updatedConflict = conflictService.update(id, conflictCreateDTO);
        return ResponseEntity.ok(updatedConflict);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConflict(@PathVariable Long id) {
        conflictService.delete(id);
        return ResponseEntity.noContent().build();
    }
}