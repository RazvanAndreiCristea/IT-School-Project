package org.example.controller;

import jakarta.validation.Valid;
import org.example.models.dtos.CustomResponseDTO;
import org.example.models.dtos.electronicsDTO.CreateElectronicProductDTO;
import org.example.models.dtos.orderDTO.CreateOrderDTO;
import org.example.repository.ClientRepository;
import org.example.repository.ElectronicRepository;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/order")
    public ResponseEntity<CustomResponseDTO> createOrder(@RequestBody @Valid CreateOrderDTO createOrderDTO, BindingResult bindingResult) {

            CustomResponseDTO customResponseDTO = new CustomResponseDTO();

            if (bindingResult.hasErrors()) {
                String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
                customResponseDTO.setResponseObject(null);
                customResponseDTO.setResponseMessage(errorMessage);
                return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
            }

            customResponseDTO.setResponseObject(orderService.createOrder(createOrderDTO));
            customResponseDTO.setResponseMessage("Order created successfully");

            return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }
}