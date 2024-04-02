package com.student_management_system.project.backend.mappers;

import com.student_management_system.project.backend.dtos.SignUpDto;
import com.student_management_system.project.backend.dtos.UserDto;
import com.student_management_system.project.backend.entites.Role;
import com.student_management_system.project.backend.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    RoleMapper roleMapper;

    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.firstName( user.getFirstName() );
        userDto.id( user.getId() );
        userDto.lastName( user.getLastName() );
        userDto.login( user.getLogin() );
        userDto.role( roleMapper.toRoleDto(user.getRole()).getRoleName() );

        return userDto.build();
    }

    @Mapping(target = "password", ignore = true)
    public User signUpToUser(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( signUpDto.firstName() );
        user.lastName( signUpDto.lastName() );
        user.login( signUpDto.login() );
        user.role(new Role(signUpDto.roleId()));

        return user.build();
    }

}
