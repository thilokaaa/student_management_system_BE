package com.student_management_system.project.backend.dtos;

public record SignUpDto (String firstName, String lastName, String login, char[] password, Long roleId) { }
