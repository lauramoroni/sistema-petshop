package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.repositories.MedVetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public MedVet login(String crmv, String senha) {
        // Busca o veterinário pelo CRMV e senha
        MedVet veterinario = medVetRepository.findMedVetByCrmv(crmv);
        if (veterinario != null && veterinario.getSenha().equals(senha)) {
            return veterinario;
        }
        return null;
    }

    public MedVet FindByCRMV(String crmv) {
        return medVetRepository.findMedVetByCrmv(crmv);
    }

    public MedVet update( MedVet medVet, String crmv ){
        MedVet medVetUpdate = FindByCRMV(crmv);
        medVetUpdate.setCelular(medVet.getCelular());
        medVetUpdate.setNome(medVet.getNome());
        medVetUpdate.setEmail(medVet.getEmail());
        medVetUpdate.setCpf(medVet.getCpf());
        medVetUpdate.setEndereco(medVet.getEndereco());
        medVetUpdate.setTelefone(medVet.getTelefone());
        medVetUpdate.setEspecialidade(medVet.getEspecialidade());

        return medVetRepository.save(medVetUpdate);
    }

    public void delete(String crmv) {
        MedVet medVetDelete = FindByCRMV(crmv);
        medVetRepository.delete(medVetDelete);
    }
}
