package com.bm.client_service.service;

import com.bm.client_service.dto.TablesRequestDTO;
import com.bm.client_service.dto.TablesResponseDTO;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.RestaurantTable;
import com.bm.client_service.repository.BookingRepository;
import com.bm.client_service.repository.RestaurantTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RestaurantTableRepository restaurantTableRepository) {
        this.bookingRepository = bookingRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public List<TablesResponseDTO> getAvailableTables(TablesRequestDTO tablesRequestDTO) {
        List<TablesResponseDTO> availableTables = new ArrayList<>();

        LocalDateTime startOfWantedDate = LocalDateTime.parse(tablesRequestDTO.getBookTime());
        LocalDateTime endOfWantedDate = startOfWantedDate.plusHours(2);
        log.info("Start of Wanted Date : {} End of Wanted Date : {}", startOfWantedDate, endOfWantedDate);

        List<RestaurantTable> tables = restaurantTableRepository.findAvailableNative(startOfWantedDate, endOfWantedDate);
        log.info("Bookings Found : {}", tables);

        for(var table : tables) {
            TablesResponseDTO tablesResponseDTO = new TablesResponseDTO();
            tablesResponseDTO.setId(String.valueOf(table.getId()));
            availableTables.add(tablesResponseDTO);
        }
        log.info("Available Tables Found : {}", availableTables);

        return availableTables;
    }
}
