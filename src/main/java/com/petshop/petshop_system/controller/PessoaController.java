package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Pessoa;
import com.petshop.petshop_system.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Método para listar todas as pessoas (ou subclasses, como Cliente)
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok().body(pessoas);
    }

    // Adicionar mais métodos que podem ser úteis, se necessário

}
