package com.student_management_system.project.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_management_system.project.backend.dtos.FacultyDto;
import com.student_management_system.project.backend.services.FacultyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/faculty")
@RestController
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {
        List<FacultyDto> facultyDtos = facultyService.getAllFaculties();
        return ResponseEntity.ok(facultyDtos);
    }
    
}
