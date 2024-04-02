package com.student_management_system.project.backend.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    private Long id;

    private String studentId;

    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    private String birthDate;

    private FacultyWithoutDepartmentDto faculty;

    private DepartmentDto department;
        
}
