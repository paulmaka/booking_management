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

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Ввод времени для бронирования столика")
    @PostMapping("/time")
    public ResponseEntity<List<TablesResponseDTO>> getAvailableTables(@RequestBody TablesRequestDTO tablesRequestDTO) {
        List<TablesResponseDTO> availableTables = bookingService.getAvailableTables(tablesRequestDTO);

        return ResponseEntity.ok(availableTables);
    }

    @Operation(summary = "Ввод данных клиента")
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Validated({Default.class}) @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.createBooking(bookingRequestDTO);

        return ResponseEntity.ok(bookingResponseDTO);
    }


}
