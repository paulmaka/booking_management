package com.bm.client_service.controller;

import com.bm.client_service.dto.ClientRequestDTO;
import com.bm.client_service.dto.ClientResponseDTO;
import com.bm.client_service.dto.validators.CreateClientValidationGroup;
import com.bm.client_service.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "API for managing clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Get all clients")
    public ResponseEntity<List<ClientResponseDTO>> getClients() {
        List<ClientResponseDTO> clientResponseDTOs = clientService.getClients();

        return ResponseEntity.ok().body(clientResponseDTOs);
    }

    @PostMapping
    @Operation(summary = "Create new client")
    public ResponseEntity<ClientResponseDTO> createClient(@Validated({Default.class, CreateClientValidationGroup.class}) @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);

        return ResponseEntity.ok().body(clientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a client")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.updateClient(id, clientRequestDTO);

        return ResponseEntity.ok().body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}
