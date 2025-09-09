package com.bm.client_service.controller;

import com.bm.client_service.dto.OrderRequestDTO;
import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.service.BookingService;
import com.bm.client_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер формирования заказов, принимает http запросы и вызывает соответствующую логику сервиса, возвращает код и тело ответа.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Возвращает список всех блюд из таблицы dishes
     * @return ResponseEntity с кодом 200 и телом, содержащим список всех блюд, имеющихся в таблице, с их описанием, типом и стоимостью
     */
    @Operation(summary = "Выводит список из всех блюд.")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllDishes(){
        List<OrderResponseDTO> listOfDishes = orderService.getAllDishes();

        return ResponseEntity.ok(listOfDishes);
    }

    /**
     * Создаёт новые заказы клиента, оформившего данную бронь
     * @param listOfOrders тело запроса, содержащее список выбранных блюд к данной брони
     * @return ResponseEntity с кодом 200 и телом, содержащим информацию о выбранном блюде
     */
    @Operation(summary = "Создаёт заказ к брони.")
    @PostMapping
    public ResponseEntity<List<OrderResponseDTO>> createOrders(@RequestBody List<OrderRequestDTO> listOfOrders){
        List<OrderResponseDTO> listOfDishesInOrders = orderService.createOrders(listOfOrders);

        return ResponseEntity.ok(listOfDishesInOrders);
    }
    
}
