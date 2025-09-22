package com.bm.client_service.service;

import billing.BillingResponse;
import com.bm.client_service.dto.OrderRequestDTO;
import com.bm.client_service.dto.OrderResponseDTO;
import com.bm.client_service.exception.BookingNotFoundException;
import com.bm.client_service.exception.DishNotFoundException;
import com.bm.client_service.grpc.BillingServiceGrpcOrder;
import com.bm.client_service.model.Booking;
import com.bm.client_service.model.Client;
import com.bm.client_service.model.Dish;
import com.bm.client_service.model.Order;
import com.bm.client_service.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookingService bookingService;
    @Mock
    private DishService dishService;
    @Mock
    private BillingServiceGrpcOrder billingServiceGrpcOrder;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrdersSuccessfulTest() {
        // Arrange
        BillingResponse response = BillingResponse.newBuilder().setStatus("responseStatus").build();
        when(billingServiceGrpcOrder.createBilling(any())).thenReturn(response);

        String bookId = UUID.randomUUID().toString();
        String dishName = "Potato";

        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setName("name");
        client.setEmail("email@example.com");

        Booking booking = new Booking();
        booking.setId(UUID.fromString(bookId));
        booking.setBookDate(LocalDateTime.parse("2025-08-15T10:10:00"));
        booking.setRegisterDate(LocalDateTime.parse("2025-08-14T10:10:00"));
        booking.setClient(client);

        when(bookingService.findBooking(bookId)).thenReturn(Optional.of(booking));

        Dish dish = new Dish();
        dish.setId(UUID.randomUUID());
        dish.setName(dishName);
        dish.setCategory("category");
        dish.setCost(1000);
        dish.setDescription("description");

        when(dishService.getDishByName(dishName)).thenReturn(Optional.of(dish));

        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> {
            Order order = inv.getArgument(0);
            order.setId(UUID.randomUUID());
            return order;
        });

        List<OrderRequestDTO> orderRequestDTOList = new ArrayList<>();
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setBookId(bookId);
        orderRequestDTO.setDishName(dishName);
        orderRequestDTOList.add(orderRequestDTO);

        // Act
        List<OrderResponseDTO> actual = orderService.createOrders(orderRequestDTOList);
        List<OrderResponseDTO> expected = new ArrayList<>();
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setDescription("description");
        orderResponseDTO.setCategory("category");
        orderResponseDTO.setDishName(dishName);
        orderResponseDTO.setCost(String.valueOf(1000));
        expected.add(orderResponseDTO);

        // Assert
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCategory(), actual.get(i).getCategory());
            assertEquals(expected.get(i).getCost(), actual.get(i).getCost());
            assertEquals(expected.get(i).getDescription(), actual.get(i).getDescription());
            assertEquals(expected.get(i).getDishName(), actual.get(i).getDishName());
        }

    }

    @Test
    void createOrderBookingNotFoundTest() {
        // Arrange

        String bookId = UUID.randomUUID().toString();

        when(bookingService.findBooking(bookId)).thenReturn(Optional.empty());

        List<OrderRequestDTO> orderRequestDTOList = new ArrayList<>();
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setBookId(bookId);
        orderRequestDTO.setDishName("Potato");
        orderRequestDTOList.add(orderRequestDTO);

        // Act & Assert
        BookingNotFoundException exception = assertThrows(BookingNotFoundException.class, () -> orderService.createOrders(orderRequestDTOList));
        assertEquals("Booking not found", exception.getMessage());
    }

    @Test
    void createOrderDishNotFoundTest() {
        // Arrange

        String bookId = UUID.randomUUID().toString();

        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setName("name");
        client.setEmail("email@example.com");

        Booking booking = new Booking();
        booking.setId(UUID.fromString(bookId));
        booking.setBookDate(LocalDateTime.parse("2025-08-15T10:10:00"));
        booking.setRegisterDate(LocalDateTime.parse("2025-08-14T10:10:00"));
        booking.setClient(client);

        when(bookingService.findBooking(bookId)).thenReturn(Optional.of(booking));

        when(dishService.getDishByName(any())).thenReturn(Optional.empty());

        List<OrderRequestDTO> orderRequestDTOList = new ArrayList<>();
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setBookId(bookId);
        orderRequestDTO.setDishName("Potato");
        orderRequestDTOList.add(orderRequestDTO);

        // Act & Assert
        DishNotFoundException exception = assertThrows(DishNotFoundException.class, () -> orderService.createOrders(orderRequestDTOList));
        assertEquals("Dish Potato not found", exception.getMessage());
    }
}