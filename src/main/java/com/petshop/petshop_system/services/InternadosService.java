package com.petshop.petshop_system.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petshop.petshop_system.entities.Internados;
import com.petshop.petshop_system.repositories.InternadosRepository;

@Service
public class InternadosService {

    @Autowired
    InternadosRepository InternadosRepository;

    public List<Internados> findAll() {
        return InternadosRepository.findAll();
    }

    public Internados insert(Internados Internados) {
        return InternadosRepository.save(Internados);
    }
}
