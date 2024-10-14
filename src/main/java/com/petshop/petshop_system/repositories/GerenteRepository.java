package com.petshop.petshop_system.repositories;

import com.petshop.petshop_system.entities.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GerenteRepository extends JpaRepository<Gerente, String> {
    Gerente findGerenteByLogin(String login);
}
