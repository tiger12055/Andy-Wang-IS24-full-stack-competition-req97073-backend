package com.example.project.service.impl;

import com.example.project.entity.Developer;
import com.example.project.repository.DeveloperRepository;
import com.example.project.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;
    @Override
    public Developer addDeveloper(Developer developer) {
        Optional<Developer> existingDeveloper = developerRepository.findByDeveloperName(developer.getDeveloperName());

        if (existingDeveloper.isPresent()) {
            throw new IllegalArgumentException("Developer with the given name already exists.");
        }
        // Save the developer to the database
        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> getAllDevelopers(){
        return developerRepository.findAll();
    }
}

