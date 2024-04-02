package com.student_management_system.project.backend.mappers;

import org.mapstruct.Mapper;

import com.student_management_system.project.backend.dtos.RoleDto;
import com.student_management_system.project.backend.entites.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toRoleDto(Role role);
    
}
