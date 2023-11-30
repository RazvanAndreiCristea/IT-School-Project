package org.example.models.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Order {
    private long id;
    private long customerId;
    private long electronicProductId;
    private double totalPrice;
}