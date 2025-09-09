package com.bm.client_service.service;

import com.bm.client_service.model.RestaurantTable;
import com.bm.client_service.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис, содержит логику работы с сущностями столов.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;

    @Autowired
    public RestaurantTableService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    /**
     * Ищет доступные в данный промежуток времени не забронированные столы
     * @param startOfWantedDate время начала промежутка
     * @param endOfWantedDate время окончания промежутка
     * @return список доступных в этот промежуток времени столов
     */
    public List<RestaurantTable> findAvailableTables(LocalDateTime startOfWantedDate, LocalDateTime endOfWantedDate){
        List<RestaurantTable> tables = restaurantTableRepository.findAvailableTables(startOfWantedDate, endOfWantedDate);

        return tables;
    }

    public Optional<RestaurantTable> findRestaurantTableById(Long restaurantTableId){
        return restaurantTableRepository.findById(restaurantTableId);
    }

}
