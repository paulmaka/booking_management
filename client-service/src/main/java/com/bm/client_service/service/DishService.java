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

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<OrderResponseDTO> getAllDishes() {
        List<Dish> listOfDishes = dishRepository.findAll();

        List<OrderResponseDTO> listOfDishesDTO = listOfDishes.stream().map(OrderMapper::toDTO).toList();
        return listOfDishesDTO;
    }

    public Optional<Dish> getDishByName(String name) {
        return dishRepository.findByName(name);
    }
}
