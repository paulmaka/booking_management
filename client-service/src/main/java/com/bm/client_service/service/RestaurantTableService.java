package com.bm.client_service.service;

import com.bm.client_service.model.RestaurantTable;
import com.bm.client_service.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;

    @Autowired
    public RestaurantTableService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public List<RestaurantTable> findAvailableTables(LocalDateTime startOfWantedDate, LocalDateTime endOfWantedDate){
        List<RestaurantTable> tables = restaurantTableRepository.findAvailableTables(startOfWantedDate, endOfWantedDate);

        return tables;
    }

    public Optional<RestaurantTable> findRestaurantTableById(Long restaurantTableId){
        return restaurantTableRepository.findById(restaurantTableId);
    }

}
