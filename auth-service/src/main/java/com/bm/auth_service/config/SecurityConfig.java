package com.bm.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс конфигурации для работы создания бинов.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
public class SecurityConfig {

    /**
     * Строится цепочка фильтров, через которую пройдут все HTTP запросы.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())   // разрешает любой запрос без аутентификации
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * Создание бина - кодировщика паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
