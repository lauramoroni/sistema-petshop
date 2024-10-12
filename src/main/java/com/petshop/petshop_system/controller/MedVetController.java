package com.petshop.petshop_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.services.AnimalService;
import com.petshop.petshop_system.services.MedVetService;

@Controller
@RequestMapping("/veterinario")
public class MedVetController {

    @Autowired
    MedVetService medVetService;
    @Autowired
    AnimalService animalService;

    // Página de login veterinário (quem cadastra é o gerente)
    @GetMapping("/login")
    public String formularioLogin() {
        return "/medvet/login";
    }

    // Página de listagem dos animais que o veterinário atende
    public String listAnimal(@PathVariable String crmv, Model model) {
    MedVet veterinario = medVetService.FindByCRMV(crmv);
    
    if (veterinario == null) {
        return "redirect:/veterinario/login";  // Redireciona para o login se o CRMV não for encontrado
    }

    List<Animal> animais = animalService.findByMedVet(veterinario);

    // Passa os dados dos animais para a view
    model.addAttribute("animais", animais);
    return "medvet/list_animal";
}

    // Pagina de cadastro de animal
    @GetMapping("/{crmv}/{id_cliente}")
    public String formularioAnimal() {
        return "/medvet/form_animal";
    }
}
