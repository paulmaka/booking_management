package com.bm.client_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @NotNull
    private int countOfUsage = 0;

    public RestaurantTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getCountOfUsage() {
        return countOfUsage;
    }

    public void setCountOfUsage(int countOfUsage) {
        this.countOfUsage = countOfUsage;
    }
}
