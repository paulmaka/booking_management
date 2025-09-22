package com.bm.client_service.service;


import com.bm.client_service.dto.OrderRequestDTO;
import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.exception.BookingNotFoundException;
import com.bm.client_service.exception.DishNotFoundException;
import com.bm.client_service.grpc.BillingServiceGrpcOrder;
import com.bm.client_service.mapper.OrderMapper;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.Dish;
import com.bm.client_service.model.Order;
import com.bm.client_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис, содержит логику создания новых заказов блюд.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final DishService dishService;
    private final ClientService clientService;
    private final BookingService bookingService;
    private final BillingServiceGrpcOrder billingServiceGrpcOrder;

    @Autowired
    public OrderService(OrderRepository orderRepository, DishService dishService,  ClientService clientService,
                        BookingService bookingService,  BillingServiceGrpcOrder billingServiceGrpcOrder)
    {
        this.orderRepository = orderRepository;
        this.dishService = dishService;
        this.clientService = clientService;
        this.bookingService = bookingService;
        this.billingServiceGrpcOrder = billingServiceGrpcOrder;
    }

    /**
     * Возвращает список всех блюд, которые есть в меню.
     * @return список DTO-ов, содержащий информацию о каждом блюде
     */
    public List<OrderResponseDTO> getAllDishes() {
        List<OrderResponseDTO> listOfDishes = dishService.getAllDishes();

        return listOfDishes;
    }

    /**
     * Возвращает список блюд, добавленных в заказ к данной брони. На вход поступают выбранные доступные блюда.
     * В случае, если блюдо или бронь не были найдены выбрасывается соответсвующая ошибка для глобального обработчика ошибок.
     * Отправляет в сервис созда
     * @param listOfOrdersDTO список из DTO-ов, содержащий название каждого блюда (они уникальны), выбранного для заказа, и номер брони.
     * @return список DTO-ов, содержащий информацию о каждом выбранном блюде, добавленном в заказ.
     */
    public List<OrderResponseDTO> createOrders(List<OrderRequestDTO> listOfOrdersDTO) {
        List<OrderResponseDTO> listOfDishesInOrders = new ArrayList<>();
        List<String> costs = new ArrayList<>();

        for (var orderDTO : listOfOrdersDTO) {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

            Optional<Booking> booking = bookingService.findBooking(orderDTO.getBookId());
            if (booking.isEmpty()) {
                throw new BookingNotFoundException("Booking not found");
            }
            Client client = booking.get().getClient();

            Optional<Dish> dish = dishService.getDishByName(orderDTO.getDishName());
            if (dish.isEmpty()) {
                throw new DishNotFoundException("Dish " + orderDTO.getDishName() + " not found");
            }

            Order order = new Order();
            order.setClient(client);
            order.setDish(dish.get());

            Order addedOrder = orderRepository.save(order);

            orderResponseDTO = OrderMapper.toDTO(addedOrder.getDish());

            costs.add(orderResponseDTO.getCost());
            listOfDishesInOrders.add(orderResponseDTO);
        }

        String status = billingServiceGrpcOrder.createBilling(costs).getStatus();

        log.info("Billing created with status: {}", status);

        return listOfDishesInOrders;
    }
}
