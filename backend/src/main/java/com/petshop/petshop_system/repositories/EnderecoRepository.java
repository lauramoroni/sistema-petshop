package com.petshop.petshop_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop_system.entities.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{

    
}