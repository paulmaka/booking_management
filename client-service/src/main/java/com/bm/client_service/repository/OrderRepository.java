package com.bm.client_service.repository;

import com.bm.client_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Класс-репозиторий для работы с таблицей заказов.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
