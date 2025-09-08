package com.bm.client_service.mapper;

import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.model.Dish;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Dish dish) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setDishName(dish.getName());
        orderResponseDTO.setCost(String.valueOf(dish.getCost()));
        orderResponseDTO.setCategory(dish.getCategory());
        orderResponseDTO.setDescription(dish.getDescription());

        return orderResponseDTO;
    }

}
