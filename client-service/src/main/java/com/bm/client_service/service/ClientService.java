package com.bm.client_service.service;

import com.bm.client_service.dto.ClientRequestDTO;
import com.bm.client_service.dto.ClientResponseDTO;
import com.bm.client_service.exception.ClientNotFoundException;
import com.bm.client_service.exception.EmailAlreadyExistsException;
import com.bm.client_service.grpc.BillingServiceGrpcClient;
import com.bm.client_service.kafka.KafkaProducer;
import com.bm.client_service.mapper.ClientMapper;
import com.bm.client_service.model.Client;
import com.bm.client_service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public ClientService(ClientRepository clientRepository, BillingServiceGrpcClient billingServiceGrpcClient,  KafkaProducer kafkaProducer) {
        this.clientRepository = clientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<ClientResponseDTO> getClients() {
        List<Client> clients = clientRepository.findAll();

        List<ClientResponseDTO> clientResponseDTOs = clients.stream().map(ClientMapper::toDTO).toList();

        return clientResponseDTOs;
    }

    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        if (clientRepository.existsByEmail(clientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Such email already used by user: " + clientRequestDTO.getEmail());
        }

        Client newClient = clientRepository.save(ClientMapper.toModel(clientRequestDTO));

        billingServiceGrpcClient.createBillingAccount(newClient.getId().toString(), newClient.getName(), newClient.getEmail());
        kafkaProducer.sendEvent(newClient);

        return ClientMapper.toDTO(newClient);
    }

    public ClientResponseDTO updateClient(UUID id, ClientRequestDTO clientRequestDTO) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id));

        if (clientRepository.existsByEmailAndIdNot(clientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Such email already used by user: " + clientRequestDTO.getEmail());
        }

        client.setName(clientRequestDTO.getName());
        client.setEmail(clientRequestDTO.getEmail());
        client.setTableNumber(Integer.parseInt(clientRequestDTO.getTableNumber()));

        Client updatedClient = clientRepository.save(client);

        return ClientMapper.toDTO(updatedClient);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
