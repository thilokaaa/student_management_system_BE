package com.student_management_system.project.backend.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.student_management_system.project.backend.dtos.StudentDto;
import com.student_management_system.project.backend.dtos.Request.AddStudentRequestDto;
import com.student_management_system.project.backend.entites.Department;
import com.student_management_system.project.backend.entites.Faculty;
import com.student_management_system.project.backend.entites.Student;
import com.student_management_system.project.backend.entites.User;
import com.student_management_system.project.backend.exceptions.AppException;
import com.student_management_system.project.backend.mappers.DepartmentMapper;
import com.student_management_system.project.backend.mappers.FacultyMapper;
import com.student_management_system.project.backend.mappers.StudentMapper;
import com.student_management_system.project.backend.repositories.StudentRepository;
import com.student_management_system.project.backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final FacultyMapper facultyMapper;

    private final DepartmentMapper departmentMapper;

    public StudentDto addStudent(AddStudentRequestDto requestDto) {
        Optional<Student> studeOptional = studentRepository.findByStudentId(requestDto.getEmail());

        if (studeOptional.isPresent()) {
            throw new AppException("Student already exists", HttpStatus.BAD_REQUEST);
        }

        Student student = new Student();
        student.setEmail(requestDto.getEmail());
        student.setStudentId(requestDto.getStudentId());
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());
        student.setMiddleName(requestDto.getMiddleName());
        student.setBirthDate(requestDto.getBirthDate().toInstant());
        student.setFaculty(new Faculty(requestDto.getFacultyId()));
        student.setDepartment(new Department(requestDto.getDepartmentId()));
        student.setIsActive(true);

        student = studentRepository.save(student);

        return studentMapper.toStudentDto(student);

    }

    public StudentDto getStudent(String studentId) {
        Optional<Student> studentOptional = studentRepository.findById(Long.parseLong(studentId));
        
        if (!studentOptional.isPresent()) {
            throw new AppException("Student not exists", HttpStatus.BAD_REQUEST);
        }

        Student student = studentOptional.get();

        Date myDate = Date.from(student.getBirthDate());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(myDate);

        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setMiddleName(student.getMiddleName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setBirthDate(formattedDate);
        studentDto.setFaculty(facultyMapper.toFacultyWithoutDepartmentDto(student.getFaculty()));
        studentDto.setDepartment(departmentMapper.toDepartmentDto(student.getDepartment()));

        return studentDto;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return studentMapper.toStudentDtoList(students);
    }

    public List<StudentDto> removeUser(String userId) {
        Optional<Student> studentOptional = studentRepository.findById(Long.parseLong(userId));
        
        if (!studentOptional.isPresent()) {
            throw new AppException("Student not exists", HttpStatus.BAD_REQUEST);
        }

        Student student = studentOptional.get();
        student.setIsActive(false);

        studentRepository.save(student);

        List<Student> students = studentRepository.findByIsActiveTrue();

        return studentMapper.toStudentDtoList(students);
    }
    
}
