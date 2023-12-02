package org.example.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
@Table(name = "Electronics")
public class ElectronicProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private double price;
    @Column(name = "weight")
    private int weight;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_manufacture")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfManufacture;
    @ManyToMany(mappedBy = "electronicProducts")
    private Set<Client> clients;

    public ElectronicProduct(String brand, String model, double price, int weight) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.weight = weight;
    }
}