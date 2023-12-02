package org.example.models.dtos.orderDTO;

import jakarta.persistence.*;
import lombok.*;
import org.example.models.entities.Client;
import org.example.models.entities.ElectronicProduct;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderDTO {
    private long clientId;
    private long electronicProductId;
    private double totalPrice;
}