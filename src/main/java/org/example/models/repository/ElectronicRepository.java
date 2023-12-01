package org.example.models.repository;

import org.example.models.entities.ElectronicProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicRepository extends JpaRepository<ElectronicProduct, Long> {

}