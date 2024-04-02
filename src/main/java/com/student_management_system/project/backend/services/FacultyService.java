package com.student_management_system.project.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student_management_system.project.backend.dtos.FacultyDto;
import com.student_management_system.project.backend.entites.Faculty;
import com.student_management_system.project.backend.mappers.FacultyMapper;
import com.student_management_system.project.backend.repositories.FacultyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final FacultyMapper facultyMapper;

    public List<FacultyDto> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();

        return facultyMapper.toFacultyDtoList(faculties);
    }
    
}
