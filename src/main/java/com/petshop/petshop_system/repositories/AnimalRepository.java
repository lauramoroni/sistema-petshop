package com.petshop.petshop_system.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.MedVet;

public interface AnimalRepository extends JpaRepository<Animal, UUID>{
    // Método para buscar animais atendidos por um determinado veterinário
    List<Animal> findByMedVet(MedVet medVet);

}
