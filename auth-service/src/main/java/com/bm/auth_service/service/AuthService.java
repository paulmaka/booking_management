package com.bm.auth_service.service;

import com.bm.auth_service.dto.LoginRequestDTO;
import com.bm.auth_service.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Класс-сервис, содержит логику аутентификации и валидации JWT токена.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // Bean создаётся в SecurityConfig
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Аутентификация пользователя. Проверяет наличие такого пользователя в таблице активных пользователей при помощи UserService,
     * проверяет соответствие зашифрованного пароля при помощи PasswordEncoder,
     * и в зависимости от наличия возвращает либо JWT токен, либо null, используя объект класса JwtUtils
     * @param loginRequestDTO тело запроса пользователя, содержащее логин и пароль пользователя.
     * @return Optional либо содержащий null, либо токен.
     */
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    /**
     * Валидация токена. Проверяет составляющие токена на предмет валидности при помощи JwtUtil,
     * и в зависимости от результата возвращает true или false
     * @param token токен пользователя из заголовка аутентификации в виде строки.
     * @return boolean значение в зависимости от валидности токена
     */
    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
