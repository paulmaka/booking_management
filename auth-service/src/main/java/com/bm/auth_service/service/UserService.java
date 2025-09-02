package com.bm.auth_service.service;

import com.bm.auth_service.dto.UserRequestDTO;
import com.bm.auth_service.dto.UserResponseDTO;
import com.bm.auth_service.exception.EmailAlreadyExistsException;
import com.bm.auth_service.mapper.UserMapper;
import com.bm.auth_service.model.User;
import com.bm.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Класс-сервис, содержит логику, связанную с работой с сущностью пользователя.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Ищет пользователя в таблице активных пользователей по адресу электронной почты.
     * @param email адрес электронной почты.
     * @return Optional, содержащий либо объект найденного пользователя, либо null, в случае если найти не удалось.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Создаёт нового пользователя в таблице активных пользователей в соответствии с переданной пользователем информацией
     * @param userRequestDTO тело запроса, содержащее имя пользователя, почту и пароль.
     * @return DTO с персональным JWT токеном, id, username и email.
     */
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Such email: " + userRequestDTO.getEmail() + " already exists");
        }

        User newUser = UserMapper.toModel(userRequestDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);

        return UserMapper.toDTO(newUser);
    }
}
