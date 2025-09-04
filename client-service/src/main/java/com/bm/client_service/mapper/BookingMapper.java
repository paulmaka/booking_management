package com.bm.client_service.mapper;

import com.bm.client_service.dto.BookingRequestDTO;
import com.bm.client_service.dto.BookingResponseDTO;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.RestaurantTable;

import java.time.LocalDateTime;

public class BookingMapper {

    public static BookingResponseDTO toBookingResponseDTO(Booking booking) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setBookId(String.valueOf(booking.getId()));
        bookingResponseDTO.setName(booking.getClient().getName());
        bookingResponseDTO.setEmail(booking.getClient().getEmail());
        bookingResponseDTO.setTable(String.valueOf(booking.getTable().getId()));
        bookingResponseDTO.setBookDate(String.valueOf(booking.getBookDate()));

        return bookingResponseDTO;
    }

    public static Client toClient(BookingRequestDTO bookingRequestDTO) {
        Client client = new Client();
        client.setEmail(bookingRequestDTO.getEmail());
        client.setName(bookingRequestDTO.getName());

        return client;
    }

    public static Booking toBooking(BookingRequestDTO bookingRequestDTO, Client client, RestaurantTable restaurantTable) {
        Booking booking = new Booking();
        booking.setClient(client);
        booking.setTable(restaurantTable);
        booking.setBookDate(LocalDateTime.parse(bookingRequestDTO.getBookTime()));
        booking.setRegisterDate(LocalDateTime.now());

        return booking;
    }
}
