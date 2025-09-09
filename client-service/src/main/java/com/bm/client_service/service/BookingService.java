package com.bm.client_service.service;

import com.bm.client_service.dto.BookingRequestDTO;
import com.bm.client_service.dto.BookingResponseDTO;
import com.bm.client_service.dto.TablesRequestDTO;
import com.bm.client_service.dto.TablesResponseDTO;
import com.bm.client_service.mapper.BookingMapper;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.RestaurantTable;
import com.bm.client_service.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс-сервис, содержит логику создания новых броней.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final RestaurantTableService restaurantTableService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RestaurantTableService restaurantTableService,  ClientService clientService) {
        this.bookingRepository = bookingRepository;
        this.restaurantTableService = restaurantTableService;
        this.clientService = clientService;
    }

    /**
     * Возвращает список доступных в этот промежуток времени столиков. Принято, что клиент занимает стол до двух часов.
     * Поэтому startOfWantedDate принимается за время, введённое клиентом, а endOfWantedDate его же с прибавлением дух часов.
     * При помощи restaurantTableService находятся все столики, промежуток брони которых не пересекается с данным промежутком.
     * @param tablesRequestDTO DTO, содержащее время желаемой брони
     * @return список DTO, содержащих номера столов, доступных в необходимый промежуток времени
     */
    public List<TablesResponseDTO> getAvailableTables(TablesRequestDTO tablesRequestDTO) {
        List<TablesResponseDTO> availableTables = new ArrayList<>();

        LocalDateTime startOfWantedDate = LocalDateTime.parse(tablesRequestDTO.getBookTime());
        LocalDateTime endOfWantedDate = startOfWantedDate.plusHours(2);
        log.info("Start of Wanted Date : {} End of Wanted Date : {}", startOfWantedDate, endOfWantedDate);

        List<RestaurantTable> tables = restaurantTableService.findAvailableTables(startOfWantedDate, endOfWantedDate);
        log.info("Bookings Found : {}", tables);

        for(var table : tables) {
            TablesResponseDTO tablesResponseDTO = new TablesResponseDTO();
            tablesResponseDTO.setId(String.valueOf(table.getId()));
            availableTables.add(tablesResponseDTO);
        }
        log.info("Available Tables Found : {}", availableTables);

        return availableTables;
    }

    /**
     * Создаёт новую бронь с введёнными параметрами
     * @param bookingRequestDTO DTO, содержащее данные о клиенте, времени бронирования и номером столика
     * @return DTO ответа с информацией о брони, клиента и столе.
     */
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        Client client = BookingMapper.toClient(bookingRequestDTO);
        Optional<RestaurantTable> table = restaurantTableService.findRestaurantTableById(Long.valueOf(bookingRequestDTO.getTable()));

        if (table.isEmpty()) {

        }
        RestaurantTable restaurantTable = table.get();

        Client addedClient = clientService.save(client);
        log.info("Client Created : {}, Table created : {}", addedClient, restaurantTable);

        Booking booking = BookingMapper.toBooking(bookingRequestDTO, addedClient, restaurantTable);
        Booking addedBooking = bookingRepository.save(booking);

        return BookingMapper.toBookingResponseDTO(addedBooking);
    }

    /**
     * Ищет бронь по её ID
     * @param bookingId идентификатор брони
     * @return Optional, содержащий либо найденную бронь, либо null
     */
    public Optional<Booking> findBooking(String bookingId) {
        return bookingRepository.findById(UUID.fromString(bookingId));
    }
}
