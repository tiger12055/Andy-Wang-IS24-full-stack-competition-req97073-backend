package com.example.project.service;

import com.example.project.entity.Developer;

import java.util.List;

public interface DeveloperService {
    Developer addDeveloper(Developer developer);

    List<Developer> getAllDevelopers();
}
