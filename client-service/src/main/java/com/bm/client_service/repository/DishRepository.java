package com.bm.client_service.repository;

import com.bm.client_service.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Класс-репозиторий для работы с таблицей блюд.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, UUID> {

    Optional<Dish> findByName(String name);
}
