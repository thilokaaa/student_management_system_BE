package com.student_management_system.project.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_management_system.project.backend.entites.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, String>{

    Optional<Token> findByEmail(String email);
    
}
