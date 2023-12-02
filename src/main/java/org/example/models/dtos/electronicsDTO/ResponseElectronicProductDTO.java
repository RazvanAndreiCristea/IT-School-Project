package org.example.models.dtos.electronicsDTO;

import lombok.*;
import org.example.models.entities.Client;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseElectronicProductDTO {
    private Long id;
    private String brand;
    private String model;
    private double price;
    private int weight;
    private Date dateOfManufacture;
    private Set<Client> clients;
}