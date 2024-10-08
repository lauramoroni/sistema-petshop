package com.petshop.petshop_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop_system.entities.Especie;

public interface EspecieRepository extends JpaRepository<Especie, UUID>{
    
}
