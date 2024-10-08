package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petshop.petshop_system.entities.Pessoa;
import com.petshop.petshop_system.repositories.PessoaRepository;
import com.petshop.petshop_system.services.PessoaService;

import java.util.List;

// Camada que recebe as requisições http e encaminha diz qual funcionalidade service

@RestController
@RequestMapping("buscar-cliente")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(){
        List<Pessoa> listPessoa = pessoaService.findAll();
        return ResponseEntity.ok().body(listPessoa);
    }

    
}
