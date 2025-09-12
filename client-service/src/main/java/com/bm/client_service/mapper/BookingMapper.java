package com.bm.client_service.mapper;

import com.bm.client_service.dto.BookingRequestDTO;
import com.bm.client_service.dto.BookingResponseDTO;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.RestaurantTable;

import java.time.LocalDateTime;

/**
 * Класс с инструментами для связи сущностей клиента и брони с DTO.
 *  * @author Paul Makarenko
 *  * @version 0.0.1
 *  * @since 0.0.1
 */
public class BookingMapper {

    /**
     * Создаёт объект DTO ответа из объекта сущности брони из БД.
     * @param booking объект сущности пользователя.
     * @return DTO, соответсвующий переданной сущности.
     */
    public static BookingResponseDTO toBookingResponseDTO(Booking booking) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setBookId(String.valueOf(booking.getId()));
        bookingResponseDTO.setName(booking.getClient().getName());
        bookingResponseDTO.setEmail(booking.getClient().getEmail());
        bookingResponseDTO.setTable(String.valueOf(booking.getTable().getId()));
        bookingResponseDTO.setBookDate(String.valueOf(booking.getBookDate()));

        return bookingResponseDTO;
    }

    /**
     * Создаёт объект сущности клиента из объекта DTO запроса.
     * @param bookingRequestDTO объект DTO, переданный в качестве запроса.
     * @return объект сущности пользователя.
     */
    public static Client toClient(BookingRequestDTO bookingRequestDTO) {
        Client client = new Client();
        client.setEmail(bookingRequestDTO.getEmail());
        client.setName(bookingRequestDTO.getName());

        return client;
    }

    /**
     * Создаёт объект сущности брони из объекта DTO запроса, объекта сущности клиента и объекта сущности столика.
     * @param bookingRequestDTO объект DTO, переданный в качестве запроса.
     * @param client объект сущности клиента, необходимый для связи клиента с бронью.
     * @param restaurantTable объект сущности столика, необходимый для связи столика с бронью.
     * @return объект сущности пользователя.
     */
    public static Booking toBooking(BookingRequestDTO bookingRequestDTO, Client client, RestaurantTable restaurantTable) {
        Booking booking = new Booking();
        booking.setClient(client);
        booking.setTable(restaurantTable);
        booking.setBookDate(LocalDateTime.parse(bookingRequestDTO.getBookTime()));
        booking.setRegisterDate(LocalDateTime.now());

        return booking;
    }
}
