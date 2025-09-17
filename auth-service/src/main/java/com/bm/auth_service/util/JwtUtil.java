package com.bm.auth_service.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


/**
 * Класс инструментов для работы с JWT токеном.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class JwtUtil {

    private final Key secretKey;

    /**
     * Ключ передаётся в качестве параметре, декодируется и превращается в secretKey.
     * @param secret ключ для создания уникальной подписи токена, передаётся в виде переменной окружения JWT_SECRET
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8)); // декодирование ключа

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Генерирует новый JWT токен для пользователя при создании аккаунта или регистрации.
     * @param email адрес электронной почты пользователя для добавления в токен.
     * @param role роль пользователя (ADMIN или USER) для добавления в токен.
     * @return сгенерированный JWT токен, содержащий адрес электронной почты пользователя,
     * его роль, время существования (10 часов) и подпись.
     */
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Проверка валидности токена. Пробрасывает ошибки в случае не валидности.
     * @param token токен пользователя
     */
    // TODO разбить ошибки на разные и логировать
    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) secretKey).build().parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature");
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }

}
