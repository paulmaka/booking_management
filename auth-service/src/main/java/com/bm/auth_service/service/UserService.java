package com.bm.auth_service.service;

import com.bm.auth_service.dto.UserRequestDTO;
import com.bm.auth_service.dto.UserResponseDTO;
import com.bm.auth_service.mapper.UserMapper;
import com.bm.auth_service.model.User;
import com.bm.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {

        }
        User newUser = UserMapper.toModel(userRequestDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);

        return UserMapper.toDTO(newUser);
    }
}
