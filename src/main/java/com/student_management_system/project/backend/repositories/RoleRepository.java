package com.student_management_system.project.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_management_system.project.backend.entites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
