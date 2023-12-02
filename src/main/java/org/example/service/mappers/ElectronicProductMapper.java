package org.example.service.mappers;

import org.example.models.dtos.electronicsDTO.CreateElectronicProductDTO;
import org.example.models.dtos.electronicsDTO.ResponseElectronicProductDTO;
import org.example.models.entities.ElectronicProduct;
import org.springframework.stereotype.Component;

@Component
public class ElectronicProductMapper {
    public ElectronicProduct mapElectronicProductDTOToElectronicProduct(CreateElectronicProductDTO electronicProductDTO) {
        return new ElectronicProduct(
                electronicProductDTO.getBrand(),
                electronicProductDTO.getModel(),
                electronicProductDTO.getPrice(),
                electronicProductDTO.getWeight()
        );
    }

    public ResponseElectronicProductDTO mapElectronicProductToResponseElectronicProductDTO(ElectronicProduct electronicProduct) {
        return new ResponseElectronicProductDTO(
                electronicProduct.getId(),
                electronicProduct.getBrand(),
                electronicProduct.getModel(),
                electronicProduct.getPrice(),
                electronicProduct.getWeight(),
                electronicProduct.getDateOfManufacture(),
                electronicProduct.getClients()
        );
    }
}