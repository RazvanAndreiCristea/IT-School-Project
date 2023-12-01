package org.example.models.controller;

import jakarta.validation.Valid;
import org.example.models.dtos.clientDTO.CreateClientDTO;
import org.example.models.dtos.CustomResponseDTO;
import org.example.models.service.ClientService;
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

//    @PutMapping(path = "/update/client")
//    public ResponseEntity<CustomResponseDTO> updateClient(@RequestBody @Valid CreateClientDTO createClientDTO, BindingResult bindingResult) {
//
//        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
//
//        if (bindingResult.hasErrors()) {
//            String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
//            customResponseDTO.setResponseObject(null);
//            customResponseDTO.setResponseMessage(errorMessage);
//            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
//        }
//
//        customResponseDTO.setResponseObject(clientService.createClient(createClientDTO));
//        customResponseDTO.setResponseMessage("User created successfully");
//
//        return new ResponseEntity<>(customResponseDTO, HttpStatus.UPGRADE_REQUIRED);
//    }
}