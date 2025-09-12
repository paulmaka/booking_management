package com.bm.client_service.service;

import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.mapper.OrderMapper;
import com.bm.client_service.model.Dish;
import com.bm.client_service.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис, содержит логику работы с сущностями блюд.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     * Возвращает список всех блюд, которые есть в меню.
     * @return список DTO-ов, содержащий информацию о каждом блюде
     */
    public List<OrderResponseDTO> getAllDishes() {
        List<Dish> listOfDishes = dishRepository.findAll();

        List<OrderResponseDTO> listOfDishesDTO = listOfDishes.stream().map(OrderMapper::toDTO).toList();
        return listOfDishesDTO;
    }

    /**
     * Ищет блюдо по его названию в БД.
     * @param name строка, название блюда
     * @return Optional либо содержащий объект найденного блюда, либо null
     */
    public Optional<Dish> getDishByName(String name) {
        return dishRepository.findByName(name);
    }
}
