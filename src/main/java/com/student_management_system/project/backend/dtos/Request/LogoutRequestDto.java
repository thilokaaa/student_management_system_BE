package com.student_management_system.project.backend.dtos.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {

    @NotNull
    private Long userId;
    
}
