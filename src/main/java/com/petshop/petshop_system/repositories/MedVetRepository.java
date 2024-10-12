package com.petshop.petshop_system.repositories;

import com.petshop.petshop_system.entities.MedVet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedVetRepository extends JpaRepository<MedVet, String> {

    MedVet findMedVetByCrmv(String crmv);

    MedVet findByCrmvAndSenha(String crmv, String senha);

}
