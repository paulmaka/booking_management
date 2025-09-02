package com.bm.client_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class Tabel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

}
