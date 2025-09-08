package com.bm.client_service.service;


import com.bm.client_service.dto.OrderRequestDTO;
import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.exception.BookingNotFoundException;
import com.bm.client_service.mapper.OrderMapper;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.Dish;
import com.bm.client_service.model.Order;
import com.bm.client_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishService dishService;
    private final ClientService clientService;
    private final BookingService bookingService;

    @Autowired
    public OrderService(OrderRepository orderRepository, DishService dishService,  ClientService clientService, BookingService bookingService) {
        this.orderRepository = orderRepository;
        this.dishService = dishService;
        this.clientService = clientService;
        this.bookingService = bookingService;
    }

    public List<OrderResponseDTO> getAllDishes() {
        List<OrderResponseDTO> listOfDishes = dishService.getAllDishes();

        return listOfDishes;
    }

    public List<OrderResponseDTO> createOrders(List<OrderRequestDTO> listOfOrdersDTO) {
        List<OrderResponseDTO> listOfDishesInOrders = new ArrayList<>();

        for (var orderDTO : listOfOrdersDTO) {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

            Optional<Booking> booking = bookingService.findBooking(orderDTO.getBookId());
            if (booking.isEmpty()) {
                throw new BookingNotFoundException("Booking not found");
            }
            Client client = booking.get().getClient();
            Dish dish = dishService.getDishByName(orderDTO.getDishName()).get();

            Order order = new Order();
            order.setClient(client);
            order.setDish(dish);

            Order addedOrder = orderRepository.save(order);

            orderResponseDTO = OrderMapper.toDTO(addedOrder.getDish());
            listOfDishesInOrders.add(orderResponseDTO);
        }

        return listOfDishesInOrders;
    }
}
