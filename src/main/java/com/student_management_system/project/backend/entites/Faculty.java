package com.student_management_system.project.backend.entites;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "table_faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faculty_name", nullable = false)
    @Size(max = 100)
    private String facultyName;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Department> departments;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Student> students;

    public Faculty(long id) {
        this.id = id;
    }
    
}
