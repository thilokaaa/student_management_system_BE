package com.student_management_system.project.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_management_system.project.backend.dtos.StudentDto;
import com.student_management_system.project.backend.dtos.Request.AddStudentRequestDto;
import com.student_management_system.project.backend.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto) {
        StudentDto studentDto = studentService.addStudent(addStudentRequestDto);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping(value = "/id/{userId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(value = "userId") String userId) {
        StudentDto studentDto = studentService.getStudent(userId);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> studentDtos = studentService.getAllStudents();
        return ResponseEntity.ok(studentDtos);
    }

    //@PutMapping(value = "/remove/{userId}")


    @PutMapping(value = "/remove/{userId}")
    public ResponseEntity<List<StudentDto>> removeUser(@PathVariable(value = "userId") String userId) {
        List<StudentDto> studentDtos = studentService.removeUser(userId);
        return ResponseEntity.ok(studentDtos);
    }
    
}
