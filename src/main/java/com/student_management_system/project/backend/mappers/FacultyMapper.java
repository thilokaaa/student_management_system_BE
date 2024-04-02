package com.student_management_system.project.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.student_management_system.project.backend.dtos.FacultyDto;
import com.student_management_system.project.backend.dtos.FacultyWithoutDepartmentDto;
import com.student_management_system.project.backend.entites.Faculty;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    FacultyWithoutDepartmentDto toFacultyWithoutDepartmentDto(Faculty faculty);

    FacultyDto toFacultyDto(Faculty faculty);

    List<FacultyDto> toFacultyDtoList(List<Faculty> faculties);
    
}
