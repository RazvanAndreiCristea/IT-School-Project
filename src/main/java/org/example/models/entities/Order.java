package org.example.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "electronic_product_id")
    private ElectronicProduct electronicProduct;
    @Column(name = "total_price")
    private double totalPrice;

    public Order(Client client, ElectronicProduct electronicProduct, double totalPrice) {
        this.client = client;
        this.electronicProduct = electronicProduct;
        this.totalPrice = totalPrice;
    }
}