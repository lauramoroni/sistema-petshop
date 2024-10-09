package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.Pessoa;
import com.petshop.petshop_system.repositories.PessoaRepository;
import com.petshop.petshop_system.services.PessoaService;

import org.springframework.ui.Model;

import java.util.List;

// Camada que recebe as requisições http e encaminha diz qual funcionalidade service

@Controller
@RequestMapping("/cliente")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    //@GetMapping()
    //public ResponseEntity<List<Pessoa>> getAll(){
    //    List<Pessoa> listPessoa = pessoaService.findAll();
    //    return ResponseEntity.ok().body(listPessoa);
    //}

    // Página de cadastro do cliente
    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());

        return "cliente_form";
    }

    // Página de login do usuário
    @GetMapping("/login")
    public String formularioLogin() {
        return "login";
    }

     // Salva uma nova pessoa
     @PostMapping("/salvar")
     public String salvarCliente(@ModelAttribute Pessoa pessoa, RedirectAttributes redirectAttributes) {
         pessoaService.salvarCliente(pessoa);
         redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
         return "redirect:/cliente/cadastro";
     }
}
