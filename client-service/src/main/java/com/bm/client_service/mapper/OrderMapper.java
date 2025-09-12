package com.bm.client_service.mapper;

import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.model.Dish;

/**
 * Класс с инструментами для связи сущности заказа с DTO.
 *  * @author Paul Makarenko
 *  * @version 0.0.1
 *  * @since 0.0.1
 */
public class OrderMapper {

    /**
     * Создаёт объект DTO ответа из объекта сущности блюда из БД.
     * @param dish объект сущности блюда.
     * @return DTO, соответсвующий переданной сущности.
     */
    public static OrderResponseDTO toDTO(Dish dish) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setDishName(dish.getName());
        orderResponseDTO.setCost(String.valueOf(dish.getCost()));
        orderResponseDTO.setCategory(dish.getCategory());
        orderResponseDTO.setDescription(dish.getDescription());

        return orderResponseDTO;
    }

}
