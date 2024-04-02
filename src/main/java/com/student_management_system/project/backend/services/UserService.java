package com.student_management_system.project.backend.services;

import com.student_management_system.project.backend.dtos.CredentialsDto;
import com.student_management_system.project.backend.dtos.SignUpDto;
import com.student_management_system.project.backend.dtos.UserDto;
import com.student_management_system.project.backend.entites.Token;
import com.student_management_system.project.backend.entites.User;
import com.student_management_system.project.backend.exceptions.AppException;
import com.student_management_system.project.backend.mappers.UserMapper;
import com.student_management_system.project.backend.repositories.TokenRepository;
import com.student_management_system.project.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        Optional<User> userOptional = userRepository.findByLogin(credentialsDto.login());

        if(!userOptional.isPresent()) {
            throw new AppException("Unknown user", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public void logout(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new AppException("Invalid User", HttpStatus.BAD_REQUEST);
        }

        Optional<Token> tokenOptional = tokenRepository.findByEmail(optionalUser.get().getLogin());

        if (!tokenOptional.isPresent()) {
            throw new AppException("Invalid User", HttpStatus.BAD_REQUEST);
        }

        Token token = tokenOptional.get();
        token.setIsValid(false);

        tokenRepository.save(token);
    }

}
