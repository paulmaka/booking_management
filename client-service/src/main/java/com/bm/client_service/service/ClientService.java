package com.bm.client_service.service;

import com.bm.client_service.model.Client;
import com.bm.client_service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс-сервис, содержит логику работы с сущностями клиентов.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Сохранение в БД нового клиента
     * @param client объект, отражающий сущность клиента, который будет сохранён в БД
     * @return объект сохранённого клиента
     */
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
