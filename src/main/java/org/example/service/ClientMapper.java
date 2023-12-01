package org.example.service;

import org.example.models.dtos.clientDTO.CreateClientDTO;
import org.example.models.dtos.clientDTO.ResponseClientDTO;
import org.example.models.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client mapClientDTOToClient(CreateClientDTO clientDTO) {
        return new Client(
                clientDTO.getAge(),
                clientDTO.getFirstName(),
                clientDTO.getLastName(),
                clientDTO.getEmail()
        );
    }

    public ResponseClientDTO mapClientToResponseClientDTO(Client client) {
        return new ResponseClientDTO(
                client.getId(),
                client.getAge(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getOrders(),
                client.getElectronicProducts()
        );
    }
}