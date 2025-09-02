package com.bm.auth_service.dto;

/**
 * Класс DTO для ответа логина пользователя.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginResponseDTO {

    private final String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
