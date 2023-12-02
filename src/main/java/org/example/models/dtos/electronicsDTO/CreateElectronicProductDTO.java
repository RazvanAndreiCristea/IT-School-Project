package org.example.models.dtos.electronicsDTO;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateElectronicProductDTO {
    private String brand;
    private String model;
    private double price;
    private int weight;
}