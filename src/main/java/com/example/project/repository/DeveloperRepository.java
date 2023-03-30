package com.example.project.repository;

import com.example.project.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    Optional<Developer> findByDeveloperName(String developerName);

    @Query("SELECT MAX(d.id) FROM Developer d")
    Integer findMaxDeveloperId();
}