package com.student_management_system.project.backend.dtos.Request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudentRequestDto {

    @Pattern(regexp = "^D/[A-Z]{3}/\\d{2}/\\d{4}$", message = "Invalid Srudent ID")
    @NotNull
    private String studentId;

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private Date birthDate;

    @NotNull
    private Long facultyId;

    @NotNull
    private Long departmentId;
    
}
