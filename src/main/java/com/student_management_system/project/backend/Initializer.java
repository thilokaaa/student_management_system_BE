// package com.student_management_system.project.backend;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.student_management_system.project.backend.entites.Department;
// import com.student_management_system.project.backend.entites.Faculty;
// import com.student_management_system.project.backend.entites.Role;
// import com.student_management_system.project.backend.entites.User;
// import com.student_management_system.project.backend.repositories.FacultyRepository;
// import com.student_management_system.project.backend.repositories.RoleRepository;
// import com.student_management_system.project.backend.repositories.UserRepository;

// @Component
// public class Initializer implements CommandLineRunner {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private FacultyRepository facultyRepository;

//     @Autowired
//     private RoleRepository roleRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;
    
//     @Override
//     public void run(String... args) throws Exception {
        
//         Role role1 = new Role();
//         role1.setRoleName("SUPER_ADMIN");
//         role1 = roleRepository.save(role1);

//         Role role2 = new Role();
//         role2.setRoleName("ADMIN");
//         role2 = roleRepository.save(role2);

//         // Role role3 = new Role();
//         // role3.setRoleName("STUDENT");
//         // role3 = roleRepository.save(role3);
        
//         User adminUser = new User();
//         adminUser.setFirstName("Thiloka");
//         adminUser.setLastName("Senavirathne");
//         adminUser.setLogin("thiloka101@gmail.com");
//         adminUser.setPassword(passwordEncoder.encode("12345"));
//         adminUser.setRole(role1);

//         adminUser = userRepository.save(adminUser);

//         List<Faculty> faculties = new ArrayList<>();

//         Faculty faculty1 = new Faculty();
//         faculty1.setFacultyName("Computing");
        
//         List<Department> departments1 = new ArrayList<>();

//         Department department1 = new Department();
//         department1.setDepartmentName("Computer Science");
//         department1.setFaculty(faculty1);
//         departments1.add(department1);

//         Department department2 = new Department();
//         department2.setDepartmentName("Computer Engineering");
//         department2.setFaculty(faculty1);
//         departments1.add(department2);

//         Department department3 = new Department();
//         department3.setDepartmentName("Data Science");
//         department3.setFaculty(faculty1);
//         departments1.add(department3);

//         faculty1.setDepartments(departments1);
//         faculties.add(faculty1);

//         Faculty faculty2 = new Faculty();
//         faculty2.setFacultyName("Engineering");

//         List<Department> departments2 = new ArrayList<>();

//         Department department4 = new Department();
//         department4.setDepartmentName("Electrical and Electronic");
//         department4.setFaculty(faculty2);
//         departments2.add(department4);

//         Department department5 = new Department();
//         department5.setDepartmentName("Telecomunication");
//         department5.setFaculty(faculty2);
//         departments2.add(department5);

//         Department department6 = new Department();
//         department6.setDepartmentName("Mechanical");
//         department6.setFaculty(faculty2);
//         departments2.add(department6);

//         Department department7 = new Department();
//         department7.setDepartmentName("Bio-medical");
//         department7.setFaculty(faculty2);
//         departments2.add(department7);



//         faculty2.setDepartments(departments2);
//         faculties.add(faculty2);

//         facultyRepository.saveAll(faculties);

//     }
// }
