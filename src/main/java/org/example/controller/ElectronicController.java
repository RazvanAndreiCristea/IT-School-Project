package org.example.controller;

import jakarta.validation.Valid;
import org.example.models.dtos.CustomResponseDTO;
import org.example.models.dtos.dateDTO.UpdateDateDTO;
import org.example.models.dtos.electronicsDTO.CreateElectronicProductDTO;
import org.example.models.dtos.electronicsDTO.ResponseElectronicProductDTO;
import org.example.service.ElectronicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/electronics")
public class ElectronicController {

    private final ElectronicService electronicService;

    @Autowired
    public ElectronicController(ElectronicService electronicService) {
        this.electronicService = electronicService;
    }

    @PostMapping(path = "/create/electronic")
    public ResponseEntity<CustomResponseDTO> createElectronic(@RequestBody @Valid CreateElectronicProductDTO createElectronicDTO, BindingResult bindingResult) {

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        customResponseDTO.setResponseObject(electronicService.createElectronic(createElectronicDTO));
        customResponseDTO.setResponseMessage("Electronic created successfully");

        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CustomResponseDTO> getAllElectronics() {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        List<ResponseElectronicProductDTO> responseElectronicProductDTOList = electronicService.getAllElectronics();

        if (responseElectronicProductDTOList.isEmpty()) {
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage("No electronics found");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }

        customResponseDTO.setResponseObject(responseElectronicProductDTOList);
        customResponseDTO.setResponseMessage("All electronics retrieved successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all/greater-than")
    public ResponseEntity<CustomResponseDTO> getAllElectronicsByPriceGreaterThan(@RequestParam double price) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        List<ResponseElectronicProductDTO> responseElectronicProductDTOList = electronicService.getAllElectronicsByPriceGreaterThan(price);

        if (responseElectronicProductDTOList.isEmpty()) {
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage("No electronics found");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }

        customResponseDTO.setResponseObject(responseElectronicProductDTOList);
        customResponseDTO.setResponseMessage("All electronics retrieved successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }
    // sa facem un request de delete care sa stearga un produs electronic dupa id

    @PatchMapping(path = "/update/date-of-manufacture")
    public ResponseEntity<CustomResponseDTO> updateDateOfManufacture(@RequestParam Long id, @RequestBody UpdateDateDTO dateOfManufacture) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        ResponseElectronicProductDTO responseElectronicProductDTO = electronicService.updateDateOfManufacture(id, dateOfManufacture.getDateOfManufacture());

        if (responseElectronicProductDTO == null) {
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage("No electronic found");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }

        customResponseDTO.setResponseObject(responseElectronicProductDTO);
        customResponseDTO.setResponseMessage("Electronic updated successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<CustomResponseDTO> deleteElectronic(@PathVariable Long id) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        String responseMessage = electronicService.deleteElectronic(id);

        if (responseMessage == null) {
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage("No electronic found");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }

        customResponseDTO.setResponseObject(null);
        customResponseDTO.setResponseMessage(responseMessage);
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }
}