package org.example.repository;

import org.example.models.entities.ElectronicProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ElectronicRepository extends JpaRepository<ElectronicProduct, Long> {

    List<ElectronicProduct> findByPriceGreaterThan(double price);
}