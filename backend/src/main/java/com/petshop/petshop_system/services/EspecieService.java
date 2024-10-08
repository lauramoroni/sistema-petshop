package com.petshop.petshop_system.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petshop.petshop_system.entities.Especie;
import com.petshop.petshop_system.repositories.EspecieRepository;

import org.springframework.http.HttpStatus;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> findAll() {
        return especieRepository.findAll();
    }

    // Método para buscar uma espécie por ID
    public Especie findById(UUID id) {
        return especieRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie não encontrada"));
    }

    // Método para inserir uma nova espécie
    public Especie insert(Especie especie) {
        return especieRepository.save(especie);
    }

    // Método para atualizar uma espécie
    public Especie update(UUID id, Especie especie) {
        Especie existingEspecie = findById(id);
        existingEspecie.setNome(especie.getNome());
        existingEspecie.setSetor(especie.getSetor());
        return especieRepository.save(existingEspecie);
    }

    // Método para deletar uma espécie
    public void delete(UUID id) {
        Especie especie = findById(id);
        especieRepository.delete(especie);
    }
}
