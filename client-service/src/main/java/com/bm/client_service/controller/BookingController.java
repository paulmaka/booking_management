package com.bm.client_service.controller;

import com.bm.client_service.dto.BookingRequestDTO;
import com.bm.client_service.dto.BookingResponseDTO;
import com.bm.client_service.dto.TablesRequestDTO;
import com.bm.client_service.dto.TablesResponseDTO;
import com.bm.client_service.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер формирования брони, принимает http запросы и вызывает соответствующую логику сервиса, возвращает код и тело ответа.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Возвращает список номеров столиков, время бронирование которых не пересекается со временем, ведённым пользователем
     * @param tablesRequestDTO тело запроса, содержащее время, выбранное пользователем для бронирования
     * @return ResponseEntity со статусом 200 и телом в виде списка номеров не забронированных столиков
     */
    @Operation(summary = "Ввод времени для бронирования столика")
    @PostMapping("/time")
    public ResponseEntity<List<TablesResponseDTO>> getAvailableTables(@RequestBody TablesRequestDTO tablesRequestDTO) {
        List<TablesResponseDTO> availableTables = bookingService.getAvailableTables(tablesRequestDTO);

        return ResponseEntity.ok(availableTables);
    }

    /**
     * Создаёт новую бронь с введёнными параметрами
     * @param bookingRequestDTO тело запроса, содержащее данные о клиенте, времени бронирования и номером столика
     * @return ResponseEntity со статусом 200 и телом в виде информации о брони (электронной почте, имени, времени бронирования, номеру брони, номеру столика)
     */
    @Operation(summary = "Ввод данных клиента")
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Validated({Default.class}) @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.createBooking(bookingRequestDTO);

        return ResponseEntity.ok(bookingResponseDTO);
    }


}
