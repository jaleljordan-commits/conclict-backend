package com.conflicttracker.controller;

import com.conflicttracker.dto.FactionCreateDTO;
import com.conflicttracker.dto.FactionDTO;
import com.conflicttracker.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping
    public ResponseEntity<List<FactionDTO>> getAllFactions() {
        List<FactionDTO> factions = factionService.findAll();
        return ResponseEntity.ok(factions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFactionById(@PathVariable Long id) {
        FactionDTO faction = factionService.findById(id);
        return ResponseEntity.ok(faction);
    }

    @GetMapping("/conflict/{conflictId}")
    public ResponseEntity<List<FactionDTO>> getFactionsByConflictId(@PathVariable Long conflictId) {
        List<FactionDTO> factions = factionService.findByConflictId(conflictId);
        return ResponseEntity.ok(factions);
    }

    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@RequestBody FactionCreateDTO factionCreateDTO) {
        FactionDTO createdFaction = factionService.create(factionCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(
            @PathVariable Long id,
            @RequestBody FactionCreateDTO factionCreateDTO) {

        FactionDTO updatedFaction = factionService.update(id, factionCreateDTO);
        return ResponseEntity.ok(updatedFaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}