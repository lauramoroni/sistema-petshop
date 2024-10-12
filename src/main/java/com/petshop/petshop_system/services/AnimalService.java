package com.petshop.petshop_system.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.repositories.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;  

    @Autowired
    ClienteService clienteService;

    @Autowired
    MedVetService medVetService;

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
    // Certifique-se de que o cliente e o veterinário estão corretamente associados ao animal
    Cliente cliente = clienteService.findByCPF(animal.getCliente().getCpf());
    animal.setCliente(cliente);

    // Você pode precisar adicionar um campo para associar o veterinário, caso ainda não tenha feito isso
    MedVet medvet = medVetService.FindByCRMV(animal.getMedVet().getCrmv());
    animal.setMedVet(medvet);

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

