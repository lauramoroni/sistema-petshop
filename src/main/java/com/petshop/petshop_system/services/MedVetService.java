package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.Gerente;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.repositories.MedVetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class MedVetService {

    @Autowired
    private MedVetRepository medVetRepository;

    public List<MedVet> findAll() {
        return medVetRepository.findAll();
    }

    public MedVet insert(MedVet medvet) {
        return medVetRepository.save(medvet);
    }

    public MedVet FindByCRMV(String crmv) {
        return medVetRepository.findMedVetByCrmv(crmv);
    }

    public MedVet update( MedVet medVet, String crmv ){
        MedVet medVetUpdate = FindByCRMV(crmv);
        medVetUpdate.setCelular(medVetUpdate.getCelular());
        medVetUpdate.setNome(medVetUpdate.getNome());
        medVetUpdate.setEmail(medVetUpdate.getEmail());
        medVetUpdate.setCpf(medVetUpdate.getCpf());
        medVetUpdate.setEndereco(medVetUpdate.getEndereco());
        medVetUpdate.setTelefone(medVetUpdate.getTelefone());

        return medVetRepository.save(medVetUpdate);
    }

    public void delete(String crmv) {
        MedVet medVetDelete = FindByCRMV(crmv);
        medVetRepository.delete(medVetDelete);
    }
}
