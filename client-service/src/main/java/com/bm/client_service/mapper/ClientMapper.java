package com.bm.client_service.mapper;

import com.bm.client_service.dto.ClientRequestDTO;
import com.bm.client_service.dto.ClientResponseDTO;
import com.bm.client_service.model.Client;

import java.time.LocalDateTime;

public class ClientMapper {
    public static ClientResponseDTO toDTO(Client client) {
        ClientResponseDTO clientDTO = new ClientResponseDTO();

        clientDTO.setId(client.getId().toString());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setTableId(String.valueOf(client.getTableId()));
        clientDTO.setBookDate(client.getBookDate().toString());

        return clientDTO;
    }

    public static Client toModel(ClientRequestDTO clientRequestDTO) {
        Client client = new Client();

        client.setName(clientRequestDTO.getName());
        client.setEmail(clientRequestDTO.getEmail());
        client.setTableId(Integer.parseInt(clientRequestDTO.getTableId()));
        client.setBookDate(LocalDateTime.parse(clientRequestDTO.getBookDate()));
        client.setRegisterDate(LocalDateTime.now());

        return client;
    }
}
