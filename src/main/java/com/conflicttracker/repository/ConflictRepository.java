package com.conflicttracker.repository;

import com.conflicttracker.model.Conflict;
import com.conflicttracker.model.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {
    List<Conflict> findByStatus(ConflictStatus status);

    @Query("SELECT c FROM Conflict c JOIN c.countries country WHERE country.code = :countryCode")
    List<Conflict> findByCountryCode(@Param("countryCode") String countryCode);

    Optional<Conflict> findByName(String name);
}