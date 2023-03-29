package com.example.project.controller;

import com.example.project.entity.Developer;
import com.example.project.service.DeveloperService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    /**
     * This endpoint is used to add a new developer via HTTP POST.
     * It takes a Developer object as the request body and returns the saved Developer object in the HTTP response body along with a HTTP status code of 201 CREATED.
     * @param developer The Developer object to be added to the system.
     * @return ResponseEntity<Developer> The HTTP response object containing the saved Developer object and a HTTP status code of 201 CREATED.
     */
    @PostMapping()
    @Operation(summary = "Add a new developer")
    public ResponseEntity<Developer> addDeveloper(@RequestBody Developer developer) {
        Developer savedDeveloper = developerService.addDeveloper(developer);
        return new ResponseEntity<>(savedDeveloper, HttpStatus.CREATED);
    }

    /**
     * This endpoint is used to retrieve a list of all developers via HTTP GET.
     * It returns a list of all developers in the HTTP response body along with a HTTP status code of 200 OK.
     * @return List<Developer> The HTTP response object containing a list of all developers and a HTTP status code of 200 OK.
     */
    @GetMapping()
    @Operation(summary = "Get all developers")
    public List<Developer> getAllDevelopers(){
        return developerService.getAllDevelopers();
    }
}