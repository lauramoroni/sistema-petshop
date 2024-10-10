package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.Gerente;
import com.petshop.petshop_system.repositories.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente insert(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public Gerente findById(UUID id) {
        //return GerenteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente não encontrado"));
    }


    public PessoaFisica update( PessoaFisica pessoaFisica, UUID id){
        PessoaFisica pessoaFisicaId = findById(id);
        pessoaFisicaId.setCelular(pessoaFisica.getCelular());
        pessoaFisicaId.setNome(pessoaFisica.getNome());
        pessoaFisicaId.setEmail(pessoaFisica.getEmail());
        pessoaFisicaId.setSexo(pessoaFisica.getSexo());
        pessoaFisicaId.setAnimais(pessoaFisica.getAnimais());
        pessoaFisicaId.setCadUnico(pessoaFisica.getCadUnico());
        pessoaFisicaId.setCpf(pessoaFisica.getCpf());
        // pessoaFisicaId.setData_cadastro(pessoaFisica.getData_cadastro()); //data de cadastro n deve mudar
        pessoaFisicaId.setEndereco(pessoaFisica.getEndereco());
        pessoaFisicaId.setNacionalidade(pessoaFisica.getNacionalidade());
        pessoaFisicaId.setTelefone(pessoaFisica.getTelefone());

        return pessoaFisicaRepository.save(pessoaFisicaId);
    }
    public List<Gerente> listar() {
        return gerenteRepository.findAll();
    }

}
