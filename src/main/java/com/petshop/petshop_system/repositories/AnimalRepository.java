package com.petshop.petshop_system.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.MedVet;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // Método para buscar animais atendidos por um determinado veterinário
    List<Animal> findByMedVet(MedVet medVet);

    @Query("SELECT a FROM Animal a WHERE a.cliente = :cliente")
    List<Animal> findByCliente(@Param("cliente") Cliente cliente);

}
