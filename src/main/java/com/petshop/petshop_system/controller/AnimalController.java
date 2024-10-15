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
    @PostMapping("/{crmv}/cliente/{cpf}/animais")
    public String adicionarAnimal(@ModelAttribute("animal") Animal animal, @PathVariable String cpf, @PathVariable String crmv) {
        // Configurar o cliente e o veterinário no animal antes de salvar
        Cliente cliente = clienteService.findByCPF(cpf);
        MedVet medVet = medVetService.FindByCRMV(crmv); 
        animal.setCliente(cliente);
        animal.setMedVet(medVet);
        animalService.insert(animal);

        cliente.getAnimais().add(animal);

        return "redirect:/veterinario/{crmv}/cliente/{cpf}/animais"; // Redireciona para a lista de animais
    }
    
    // Detalhe do animal
    @GetMapping("{crmv}/{id_animal}")
    public String detalhesAnimal(Model model, @PathVariable Long id_animal, @PathVariable String crmv) {

        model.addAttribute("animal", animalService.findById(id_animal));
        model.addAttribute("medVets", medVetService.FindByCRMV(crmv));

        return "animais/detalhe_animal";
    }

    
    
}
