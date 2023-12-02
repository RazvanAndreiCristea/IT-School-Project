package org.example.service;

import org.example.models.dtos.orderDTO.CreateOrderDTO;
import org.example.models.dtos.orderDTO.ResponseOrderDTO;
import org.example.models.entities.Client;
import org.example.models.entities.ElectronicProduct;
import org.example.models.entities.Order;
import org.example.repository.ClientRepository;
import org.example.repository.ElectronicRepository;
import org.example.repository.OrderRepository;
import org.example.service.mappers.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ElectronicRepository electronicRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ElectronicRepository electronicRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.electronicRepository = electronicRepository;
        this.orderMapper = orderMapper;
    }

    public ResponseOrderDTO createOrder(CreateOrderDTO createorderDTO) {
        Client client = clientRepository.findById(createorderDTO.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        ElectronicProduct electronicProduct = electronicRepository.findById(createorderDTO.getElectronicProductId()).orElseThrow(() -> new RuntimeException("Electronic product not found"));

        Order order = new Order(
                client,
                electronicProduct,
                createorderDTO.getTotalPrice()
        );

        Order createDBorder = orderRepository.save(order);

        List<Order> orders = client.getOrders();
        orders.add(createDBorder);
        client.setOrders(orders);

        Set<ElectronicProduct> electronicProducts = client.getElectronicProducts();
        electronicProducts.add(electronicProduct);
        client.setElectronicProducts(electronicProducts);

        clientRepository.save(client);

        return orderMapper.mapOrderToResponseOrderDTO(createDBorder);
    }
}
