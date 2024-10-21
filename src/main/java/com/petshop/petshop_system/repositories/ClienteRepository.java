package com.petshop.petshop_system.repositories;

import com.petshop.petshop_system.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
   Cliente findByCpf(String cpf);
}
