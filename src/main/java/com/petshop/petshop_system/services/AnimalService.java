package com.petshop.petshop_system.services;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.repositories.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;  

    // Método para listar todos os animais
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    // Método para buscar um animal por ID
    public Animal findById(UUID id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrada"));
    }

    // Método para inserir um animal
    public Animal insert(Animal animal) { 
        //animal.setId(null);  // Garantir que o ID seja gerado automaticamente
        return animalRepository.save(animal);
    }

    // Método para atualizar um animal
    public Animal update(UUID id, Animal animal) {
        Animal existAnimal = findById(id);
        existAnimal.setNome(animal.getNome());
        existAnimal.setEspecie(animal.getEspecie());
        existAnimal.setEsterilizacao(animal.getEsterilizacao());
        existAnimal.setPelagem(animal.getPelagem());
        existAnimal.setRaca(animal.getRaca());
        existAnimal.setSexo(animal.getSexo());
        existAnimal.setStatus(animal.getStatus());

        return animalRepository.save(existAnimal);
    }

    //Método para deletar um animal
    public void delete(UUID id) {
        Animal animal = findById(id);
        animalRepository.delete(animal);
    }
}

