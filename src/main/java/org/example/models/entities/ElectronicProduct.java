package org.example.models.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "date_of_manufacture")
    private Date dateOfManufacture;
    @ManyToMany(mappedBy = "electronicProducts")
    private Set<Client> clients;
}