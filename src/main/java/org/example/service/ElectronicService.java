package org.example.service;

import org.example.models.dtos.electronicsDTO.CreateElectronicProductDTO;
import org.example.models.dtos.electronicsDTO.ResponseElectronicProductDTO;
import org.example.models.entities.ElectronicProduct;
import org.example.repository.ElectronicRepository;
import org.example.service.mappers.ElectronicProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ElectronicService {

    private final ElectronicRepository electronicRepository;
    private final ElectronicProductMapper electronicProductMapper;

    @Autowired
    public ElectronicService(ElectronicRepository electronicRepository, ElectronicProductMapper electronicProductMapper) {
        this.electronicRepository = electronicRepository;
        this.electronicProductMapper = electronicProductMapper;
    }

    public List<ResponseElectronicProductDTO> getAllElectronics() {
        return electronicRepository.findAll().stream().
                map(electronicProductMapper::mapElectronicProductToResponseElectronicProductDTO).toList();
    }

    public ResponseElectronicProductDTO createElectronic(CreateElectronicProductDTO createElectronicProductDTO) {
        ElectronicProduct electronicProduct = electronicProductMapper.mapElectronicProductDTOToElectronicProduct(createElectronicProductDTO);
        electronicRepository.save(electronicProduct);
        return electronicProductMapper.mapElectronicProductToResponseElectronicProductDTO(electronicProduct);
    }

    public List<ResponseElectronicProductDTO> getAllElectronicsByPriceGreaterThan(double price) {
        return electronicRepository.findByPriceGreaterThan(price).stream().
                map(electronicProductMapper::mapElectronicProductToResponseElectronicProductDTO).toList();
    }

    public ResponseElectronicProductDTO updateDateOfManufacture(Long id, Date dateOfManufacture) {
        ElectronicProduct electronicProduct = electronicRepository.findById(id).orElseThrow(() -> new RuntimeException("Electronic product not found"));
        electronicProduct.setDateOfManufacture(dateOfManufacture);
        electronicRepository.save(electronicProduct);
        return electronicProductMapper.mapElectronicProductToResponseElectronicProductDTO(electronicProduct);
    }

    public String deleteElectronic(Long id) {
        ElectronicProduct electronicProduct = electronicRepository.findById(id).orElseThrow(() -> new RuntimeException("Electronic product not found"));
        electronicRepository.delete(electronicProduct);
        return "Electronic product deleted successfully";
    }
}