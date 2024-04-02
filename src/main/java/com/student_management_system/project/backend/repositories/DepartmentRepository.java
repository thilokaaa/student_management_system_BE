package com.student_management_system.project.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student_management_system.project.backend.entites.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
}
