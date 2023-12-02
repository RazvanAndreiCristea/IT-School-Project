package org.example.models.dtos.orderDTO;

import lombok.*;
import org.example.models.dtos.clientDTO.ResponseClientDTO;
import org.example.models.dtos.electronicsDTO.ResponseElectronicProductDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseOrderDTO {
    private long id;
    private ResponseClientDTO clientId;
    private ResponseElectronicProductDTO electronicProductId;
    private double totalPrice;
}