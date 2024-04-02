package com.student_management_system.project.backend.mappers;

import org.mapstruct.Mapper;

import com.student_management_system.project.backend.dtos.DepartmentDto;
import com.student_management_system.project.backend.entites.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDepartmentDto(Department department);
    
}
