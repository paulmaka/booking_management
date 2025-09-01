package com.bm.client_service.repository;

import com.bm.client_service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
