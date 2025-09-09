package com.bm.client_service.service;

import com.bm.client_service.dto.BookingRequestDTO;
import com.bm.client_service.dto.BookingResponseDTO;
import com.bm.client_service.exception.TableNotFoundException;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.RestaurantTable;
import com.bm.client_service.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private RestaurantTableService restaurantTableService;
    @Mock
    private ClientService clientService;
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void createBookingSuccessfulTest() {
        long tableId = 1L;

        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setId(tableId);
        restaurantTable.setCountOfUsage(0);

        when(restaurantTableService.findRestaurantTableById(tableId)).thenReturn(Optional.of(restaurantTable));

        when(clientService.save(any(Client.class))).thenAnswer(inv -> {
            Client client = inv.getArgument(0);
            client.setId(UUID.randomUUID());
            return client;
        });

        when(bookingRepository.save(any(Booking.class))).thenAnswer(inv -> {
            Booking booking = inv.getArgument(0);
            booking.setId(UUID.randomUUID());
            return booking;
        });

        // Arrange
        BookingRequestDTO  bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setBookTime("2025-08-15T10:10:00");
        bookingRequestDTO.setEmail("email@example.com");
        bookingRequestDTO.setName("name");
        bookingRequestDTO.setTable(String.valueOf(tableId));

        // Act
        BookingResponseDTO bookingResponseDTO = bookingService.createBooking(bookingRequestDTO);

        // Assert
        assertNotNull(bookingResponseDTO.getBookId());
        assertEquals("name",  bookingResponseDTO.getName());
        assertEquals("email@example.com", bookingResponseDTO.getEmail());
        assertEquals("2025-08-15T10:10", bookingResponseDTO.getBookDate());
        assertEquals(String.valueOf(tableId), bookingResponseDTO.getTable());

    }

    @Test
    void createBookingTableNotFoundTest() {
        long tableId = 10000L;

        when(restaurantTableService.findRestaurantTableById(tableId)).thenReturn(Optional.empty());

        // Arrange
        BookingRequestDTO  bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setBookTime("2025-08-15T10:10:00");
        bookingRequestDTO.setEmail("email@example.com");
        bookingRequestDTO.setName("name");
        bookingRequestDTO.setTable(String.valueOf(tableId));

        // Act & Arrange
        TableNotFoundException exception = assertThrows(TableNotFoundException.class,  () -> bookingService.createBooking(bookingRequestDTO));
        assertEquals("Table 10000 not found", exception.getMessage());
    }
}