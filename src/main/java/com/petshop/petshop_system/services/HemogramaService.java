package com.petshop.petshop_system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop_system.entities.Hemograma;
import com.petshop.petshop_system.repositories.HemogramaRepository;

@Service
public class HemogramaService {

    @Autowired
    HemogramaRepository hemogramaRepository;

    public List<Hemograma> findAll() {
        return hemogramaRepository.findAll();
    }

    public Hemograma insert(Hemograma hemograma) {
        return hemogramaRepository.save(hemograma);
    }
}
