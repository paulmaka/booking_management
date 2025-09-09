package com.bm.client_service.repository;

import com.bm.client_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Класс-репозиторий для работы с таблицей броней.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

}
