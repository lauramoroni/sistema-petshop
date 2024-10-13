package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.services.AnimalService;
import com.petshop.petshop_system.services.ClienteService;
import com.petshop.petshop_system.services.MedVetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    MedVetService medVetService;

    // Formulário para adicionar um novo animal
    @GetMapping("/{crmv}/cadastro/{cpf}")
    public String criarAnimalForm(Model model, @PathVariable String crmv, @PathVariable String cpf) {
        Animal novoAnimal = new Animal();
        model.addAttribute("animal", novoAnimal);

        // Adicionar listas de clientes e veterinários
        model.addAttribute("clientes", clienteService.findByCPF(cpf));
        model.addAttribute("medVets", medVetService.FindByCRMV(crmv));

        return "animais/form_animal"; // Retorna a view do formulário
    }

    // Adicionar novo animal
    @PostMapping("/{crmv}/cadastro") // Corrigido para POST em /cadastro
    public String adicionarAnimal(@ModelAttribute("animal") Animal animal, @RequestParam String cpf, @RequestParam String crmv) {
        // Configurar o cliente e o veterinário no animal antes de salvar
        Cliente cliente = clienteService.findByCPF(cpf);
        MedVet medVet = medVetService.FindByCRMV(crmv); // Corrigido para findByCRMV
        animal.setCliente(cliente);
        animal.setMedVet(medVet);

        animalService.insert(animal);
        return "redirect:/veterinario/{crmv}/cliente/{id_cliente}/animais"; // Redireciona para a lista de animais
    }
}
