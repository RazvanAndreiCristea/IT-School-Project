package org.example.service;

import org.example.models.dtos.clientDTO.*;
import org.example.models.entities.Client;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.mapClientToResponseClientDTO(client);
    }

    public List<ResponseClientDTO> getClientsByFirstNameOrLastName(String firstName, String lastName) {
        List<Client> clientList = clientRepository.findClientsByFirstNameOrLastName(firstName, lastName);
        return clientList.stream().map(clientMapper::mapClientToResponseClientDTO).toList();
    }

    public ResponseClientDTO updateClient(PatchClientDTO patchClientDTO, long id) {
        // trebuie verificat daca clientul exista in baza de date
        // daca exista ii facem o mapare cu datele din patchClientDTO in clientul din baza de date si salvam clientul in baza de date cu datele actualizate si returnam clientul actualizat
        // daca nu exista aruncam o exceptie
        // trebuie verificat daca ce primim in patchClientDTO este este diferit de ce avem in baza de date

        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));

        // aici modificam doar campurile care au fost introduse de user restul vor ramane ca in baza de date

        if (patchClientDTO.getAge() != client.getAge() && patchClientDTO.getAge() > 0) {
            client.setAge(patchClientDTO.getAge());
        }
        if (patchClientDTO.getFirstName() != null) {
            client.setFirstName(patchClientDTO.getFirstName());
        }
        if (patchClientDTO.getLastName() != null) {
            client.setLastName(patchClientDTO.getLastName());
        }
        if (patchClientDTO.getEmail() != null) {
            client.setEmail(patchClientDTO.getEmail());
        }

        clientRepository.save(client);
        return clientMapper.mapClientToResponseClientDTO(client);
    }

    public String deleteClient(long id) {
        // trebuie verificat daca clientul exista in baza de date
        // daca exista il stergem si returnam un mesaj de succes
        // daca nu exista aruncam o exceptie

        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        clientRepository.delete(client);
        return "Client deleted successfully";
    }
}