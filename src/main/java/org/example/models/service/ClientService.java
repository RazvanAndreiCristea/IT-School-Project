package org.example.models.service;

import org.example.models.dtos.clientDTO.*;
import org.example.models.entities.Client;
import org.example.models.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ResponseClientDTO createClient(CreateClientDTO clientDTO) {
        Client client = clientMapper.mapClientDTOToClient(clientDTO);
        clientRepository.save(client);
        return clientMapper.mapClientToResponseClientDTO(client);
    }
}