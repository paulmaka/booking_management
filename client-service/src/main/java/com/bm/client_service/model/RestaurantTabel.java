package com.bm.client_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "tables")
public class RestaurantTabel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private boolean active = false;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    private int countOfUsage = 0;

    public RestaurantTabel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getCountOfUsage() {
        return countOfUsage;
    }

    public void setCountOfUsage(int countOfUsage) {
        this.countOfUsage = countOfUsage;
    }
}
