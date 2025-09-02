package com.bm.auth_service.mapper;

import com.bm.auth_service.dto.UserRequestDTO;
import com.bm.auth_service.dto.UserResponseDTO;
import com.bm.auth_service.model.User;

/**
 * Класс с инструментами для связи сущности и DTO.
 *  * @author Paul Makarenko
 *  * @version 0.0.1
 *  * @since 0.0.1
 */
public class UserMapper {

    /**
     * Создаёт объект DTO ответа из объекта сущности из БД.
     * @param user объект сущности пользователя.
     * @return DTO, соответсвующий переданной сущности.
     */
    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId().toString());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());

        return userResponseDTO;
    }

    /**
     * Создаёт объект сущности из объекта DTO запроса.
     * @param userRequestDTO объект DTO, переданный в качестве запроса.
     * @return объект сущности пользователя.
     */
    public static User toModel(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole("USER");

        return user;
    }
}
