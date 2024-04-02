package com.student_management_system.project.backend.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyDto {

    private Long id;

    private String facultyName;

    private List<DepartmentDto> departments;
    
}
