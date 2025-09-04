package com.bm.client_service.repository;

import com.bm.client_service.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long> {

    @Query(value = """
    select t.* from tables t
    where not exists (
        select 1 from bookings b
        where b.table_id = t.id
          and b.book_date < :end_ts
          and b.book_date + interval '2 hour' > :start_ts
    )
    """, nativeQuery = true)
    List<RestaurantTable> findAvailableTables(@Param("start_ts") LocalDateTime start, @Param("end_ts") LocalDateTime end);

}
