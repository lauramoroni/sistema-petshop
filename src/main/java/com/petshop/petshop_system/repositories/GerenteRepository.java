package com.petshop.petshop_system.repositories;

import com.petshop.petshop_system.entities.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GerenteRepository extends JpaRepository<Gerente, UUID> {
    Gerente findById(UUID id);
}
