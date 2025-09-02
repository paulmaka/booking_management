package com.bm.auth_service.repository;


import com.bm.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Класс-репозиторий, взаимодействующий с таблицей пользователей при помощи Hibernate JPA.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Ищет пользователя в таблице по адресу электронной почты. Запрос к БД формируется автоматически из названия метода.
     * @param email адрес электронной почты.
     * @return Optional, содержащий либо объект найденного пользователя, либо null, в случае если найти не удалось.
     */
    Optional<User> findByEmail(String email);

    /**
     * Проверяет существование пользователя с таким адресом электронной почты.
     * @param email адрес электронной почты.
     * @return boolean значение в зависимости от существования пользователя с таким адресом электронной почты.
     */
    boolean existsByEmail(String email);
}
