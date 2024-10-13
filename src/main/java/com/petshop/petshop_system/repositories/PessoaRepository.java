package com.petshop.petshop_system.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.petshop_system.entities.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, String> {


}
