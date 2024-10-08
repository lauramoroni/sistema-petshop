package com.petshop.petshop_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petshop.petshop_system.entities.Especie;
import com.petshop.petshop_system.services.EspecieService;

@RestController
@RequestMapping("especie")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    // Método para listar todas as espécies
    @GetMapping
    public ResponseEntity<List<Especie>> getAll() {
        List<Especie> especies = especieService.findAll();
        return ResponseEntity.ok().body(especies);
    }

    // Método para buscar uma espécie por ID
    @GetMapping("/{id}")
    public ResponseEntity<Especie> getById(@PathVariable UUID id) {
        Especie especie = especieService.findById(id);
        return ResponseEntity.ok().body(especie);
    }

    // Método para inserir uma nova espécie
    @PostMapping
    public ResponseEntity<Especie> insert(@RequestBody Especie especie) {
        Especie savedEspecie = especieService.insert(especie);
        return ResponseEntity.ok().body(savedEspecie);
    }

    // Método para atualizar uma espécie existente
    @PutMapping("/{id}")
    public ResponseEntity<Especie> update(@PathVariable UUID id, @RequestBody Especie especie) {
        Especie updatedEspecie = especieService.update(id, especie);
        return ResponseEntity.ok().body(updatedEspecie);
    }

    // Método para deletar uma espécie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        especieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
