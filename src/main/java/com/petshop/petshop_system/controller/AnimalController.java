package com.petshop.petshop_system.controller;

import org.springframework.web.bind.annotation.RestController;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.services.AnimalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    //@GetMapping
    //public ResponseEntity<List<Animal>> getAll(){
    //    List<Animal> listPessoa = animalService.findAll();
    //    return ResponseEntity.ok().body(listPessoa);
    //}
    
    @GetMapping("/list")
    public String getAll(Model model) {
        List<Animal> listAnimal = animalService.findAll();
        model.addAttribute("animals", listAnimal);  // Adiciona a lista de animais ao modelo
        return "animal_list";  // Retorna o nome do arquivo HTML a ser renderizado
    }

    @PostMapping
    public ResponseEntity<Animal> insert(@RequestBody Animal animal) {
        animal = animalService.insert(animal); 
        return ResponseEntity.ok().body(animal);
    }


    // Método para buscar uma espécie por ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable Long id) {
        Animal Animal = animalService.findById(id);
        return ResponseEntity.ok().body(Animal);
    }

    // Método para atualizar uma espécie existente
    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.update(id, animal);
        return ResponseEntity.ok().body(updatedAnimal);
    }

    // Método para deletar uma espécie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
