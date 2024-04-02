package com.student_management_system.project.backend.mappers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.student_management_system.project.backend.dtos.StudentDto;
import com.student_management_system.project.backend.entites.Student;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    public StudentDto toStudentDto(Student student) {

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

    public abstract List<StudentDto> toStudentDtoList(List<Student> students);
    
}
