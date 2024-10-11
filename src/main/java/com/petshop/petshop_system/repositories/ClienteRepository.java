package com.petshop.petshop_system.repositories;

import com.petshop.petshop_system.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
   Cliente findByCpf(String cpf);
}
