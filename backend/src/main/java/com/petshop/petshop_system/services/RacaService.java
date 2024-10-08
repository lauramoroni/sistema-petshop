package com.petshop.petshop_system.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petshop.petshop_system.entities.Raca;
import com.petshop.petshop_system.repositories.RacaRepository;

import org.springframework.http.HttpStatus;

@Service
public class RacaService {

    @Autowired
    private RacaRepository racaRepository;

    // Método para buscar todas as raças
    public List<Raca> findAll() {
        return racaRepository.findAll();
    }

    // Método para buscar uma raça por ID
    public Raca findById(UUID id) {
        return racaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Raça não encontrada"));
    }

    // Método para inserir uma nova raça
    public Raca insert(Raca raca) {
        return racaRepository.save(raca);
    }

    // Método para atualizar uma raça
    public Raca update(UUID id, Raca raca) {
        Raca existingRaca = findById(id);
        existingRaca.setNome(raca.getNome());
        existingRaca.setEspecie(raca.getEspecie());
        return racaRepository.save(existingRaca);
    }

    // Método para deletar uma raça
    public void delete(UUID id) {
        Raca raca = findById(id);
        racaRepository.delete(raca);
    }
}
