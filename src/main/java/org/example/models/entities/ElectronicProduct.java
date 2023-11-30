package org.example.models.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class ElectronicProduct {

    private long id;
    private String brand;
    private String model;
    private double price;
    private int weight;
    private Date dateOfManufacture;
}