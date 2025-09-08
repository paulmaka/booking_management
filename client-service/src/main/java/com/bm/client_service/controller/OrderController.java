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

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Выводит список из всех блюд.")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllDishes(){
        List<OrderResponseDTO> listOfDishes = orderService.getAllDishes();

        return ResponseEntity.ok(listOfDishes);
    }

    @Operation(summary = "Создаёт заказ к брони.")
    @PostMapping
    public ResponseEntity<List<OrderResponseDTO>> createOrders(@RequestBody List<OrderRequestDTO> listOfOrders){
        List<OrderResponseDTO> listOfDishesInOrders = orderService.createOrders(listOfOrders);

        return ResponseEntity.ok(listOfDishesInOrders);
    }
    
}
