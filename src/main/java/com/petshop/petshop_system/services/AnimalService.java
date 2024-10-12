package com.petshop.petshop_system.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.repositories.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;  

    // Método para listar todos os animais
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

public List<Animal> findByMedVet(MedVet medVet) {
    return animalRepository.findByMedVet(medVet);  // Implementar no repository
}

    // Método para buscar um animal por ID
    public Animal findById(Long id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrada"));
    }

    // Método para inserir um animal
    public Animal insert(Animal animal) { 
        //animal.setId(null);  // Garantir que o ID seja gerado automaticamente
        return animalRepository.save(animal);
    }

    // Método para atualizar um animal
    public Animal update(Long id, Animal animal) {
        Animal existAnimal = findById(id);
        existAnimal.setNome(animal.getNome());
        existAnimal.setEsterilizacao(animal.getEsterilizacao());
        existAnimal.setSexo(animal.getSexo());

        return animalRepository.save(existAnimal);
    }

    //Método para deletar um animal
    public void delete(Long id) {
        Animal animal = findById(id);
        animalRepository.delete(animal);
    }
}

