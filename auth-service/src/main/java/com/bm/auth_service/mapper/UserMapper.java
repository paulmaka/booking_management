package com.bm.auth_service.mapper;

import com.bm.auth_service.dto.UserRequestDTO;
import com.bm.auth_service.dto.UserResponseDTO;
import com.bm.auth_service.model.User;


public class UserMapper {

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId().toString());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());

        return userResponseDTO;
    }

    public static User toModel(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole("USER");

        return user;
    }
}
