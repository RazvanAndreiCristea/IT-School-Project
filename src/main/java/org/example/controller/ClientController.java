package org.example.controller;

import jakarta.validation.Valid;
import org.example.models.dtos.clientDTO.CreateClientDTO;
import org.example.models.dtos.CustomResponseDTO;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(path = "/create/client")
    public ResponseEntity<CustomResponseDTO> createClient(@RequestBody @Valid CreateClientDTO createClientDTO, BindingResult bindingResult) {

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        customResponseDTO.setResponseObject(clientService.createClient(createClientDTO));
        customResponseDTO.setResponseMessage("User created successfully");

        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/client/{id}")
    public ResponseEntity<CustomResponseDTO> getClient(@PathVariable Long id) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        customResponseDTO.setResponseObject(clientService.getClient(id));
        customResponseDTO.setResponseMessage("Client retrieved successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }
}