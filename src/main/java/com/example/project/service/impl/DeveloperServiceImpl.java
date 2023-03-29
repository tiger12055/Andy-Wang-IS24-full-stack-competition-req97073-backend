package com.example.project.service.impl;

import com.example.project.entity.Developer;
import com.example.project.repository.DeveloperRepository;
import com.example.project.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;
    @Override
    public Developer addDeveloper(Developer developer) {
        Developer existingDeveloper = developerRepository.findByDeveloperName(developer.getDeveloperName());

        if (existingDeveloper != null) {
            throw new IllegalArgumentException("Developer with the given name already exists.");
        }
        // Save the developer to the database
        Developer savedDeveloper = developerRepository.save(developer);

        return savedDeveloper;
    }

    @Override
    public List<Developer> getAllDevelopers(){
        return developerRepository.findAll();
    }
}

