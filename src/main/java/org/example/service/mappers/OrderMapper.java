package org.example.service.mappers;

import org.example.models.dtos.orderDTO.CreateOrderDTO;
import org.example.models.dtos.orderDTO.ResponseOrderDTO;
import org.example.models.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ClientMapper clientMapper;
    private final ElectronicProductMapper electronicMapper;

    public OrderMapper(ClientMapper clientMapper, ElectronicProductMapper electronicMapper) {
        this.clientMapper = clientMapper;
        this.electronicMapper = electronicMapper;
    }

//    public Order mapOrderDTOToOrder(CreateOrderDTO orderDTO) {
//        return new Order(
//                orderDTO.getClientId(),
//                orderDTO.getElectronicProductId(),
//                orderDTO.getTotalPrice()
//        );
//    }

    public ResponseOrderDTO mapOrderToResponseOrderDTO(Order order) {
        return new ResponseOrderDTO(
                order.getId(),
                clientMapper.mapClientToResponseClientDTO(order.getClient()),
                electronicMapper.mapElectronicProductToResponseElectronicProductDTO(order.getElectronicProduct()),
                order.getTotalPrice()
        );
    }
}