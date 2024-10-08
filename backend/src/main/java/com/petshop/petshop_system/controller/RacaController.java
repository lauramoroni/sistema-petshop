package com.petshop.petshop_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petshop.petshop_system.entities.Raca;
import com.petshop.petshop_system.services.RacaService;

@RestController
@RequestMapping("raca")
public class RacaController {

    @Autowired
    private RacaService racaService;

    // Método para listar todas as raças
    @GetMapping
    public ResponseEntity<List<Raca>> getAll() {
        List<Raca> racas = racaService.findAll();
        return ResponseEntity.ok().body(racas);
    }

    // Método para buscar uma raça por ID
    @GetMapping("/{id}")
    public ResponseEntity<Raca> getById(@PathVariable UUID id) {
        Raca raca = racaService.findById(id);
        return ResponseEntity.ok().body(raca);
    }

    // Método para inserir uma nova raça
    @PostMapping
    public ResponseEntity<Raca> insert(@RequestBody Raca raca) {
        Raca savedRaca = racaService.insert(raca);
        return ResponseEntity.ok().body(savedRaca);
    }

    // Método para atualizar uma raça existente
    @PutMapping("/{id}")
    public ResponseEntity<Raca> update(@PathVariable UUID id, @RequestBody Raca raca) {
        Raca updatedRaca = racaService.update(id, raca);
        return ResponseEntity.ok().body(updatedRaca);
    }

    // Método para deletar uma raça
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        racaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
