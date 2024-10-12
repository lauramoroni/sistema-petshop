package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.Gerente;
import com.petshop.petshop_system.repositories.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente insert(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public Gerente findById(String id) {
        return gerenteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente não encontrado"));
    }

    public Gerente update( Gerente gerente, String id){
        Gerente gerenteUpdate = findById(id);
        gerenteUpdate.setCelular(gerenteUpdate.getCelular());
        gerenteUpdate.setNome(gerenteUpdate.getNome());
        gerenteUpdate.setEmail(gerenteUpdate.getEmail());
        gerenteUpdate.setCpf(gerenteUpdate.getCpf());
        gerenteUpdate.setEndereco(gerenteUpdate.getEndereco());
        gerenteUpdate.setTelefone(gerenteUpdate.getTelefone());

        return gerenteRepository.save(gerenteUpdate);
    }
    public List<Gerente> listar() {
        return gerenteRepository.findAll();
    }

    public void delete(String id) {
        gerenteRepository.deleteById(id);
    }

}
