package com.petshop.petshop_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop_system.entities.Raca;

public interface RacaRepository extends JpaRepository<Raca, UUID>{
    
}
