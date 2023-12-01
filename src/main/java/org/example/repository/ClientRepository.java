package org.example.repository;

import jakarta.validation.constraints.NotNull;
import org.example.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    @Override
    Optional<Client> findById(@NotNull Long aLong);
}