package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Hemograma;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.services.AnimalService;
import com.petshop.petshop_system.services.HemogramaService;
import com.petshop.petshop_system.services.MedVetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/exame")
public class HemogramaController {

    @Autowired
    HemogramaService hemogramaService;
    @Autowired
    AnimalService animalService;
    @Autowired
    MedVetService medVetService;

    // Página de adicionar um hemograma
    @GetMapping("/{id_animal}")
    public String formularioCadastro(@PathVariable Long id_animal, Model model) {

        Animal animal = animalService.findById(id_animal);

        model.addAttribute("animal", animal); 
        model.addAttribute("hemograma", new Hemograma());

        return "animais/exame_form"; 
    }

    // Método para processar o formulário do hemograma
    @PostMapping("/{id_animal}")
    public String salvarExame(@PathVariable Long id_animal, @ModelAttribute("hemograma") Hemograma hemograma, @RequestParam String crmv, Model model) {
    
        Animal animal = animalService.findById(id_animal);
        MedVet medvet = medVetService.FindByCRMV(crmv);

        if (animal != null && medvet != null) {
            // Associa o hemograma ao animal e ao veterinário
            hemograma.setAnimal(animal);
            
            // Salva o hemograma
            hemogramaService.insert(hemograma);

            // Adiciona o hemograma à lista de hemogramas do animal
            animal.getHemogramas().add(hemograma);

            // Redireciona para a página de detalhes do animal
            return "redirect:/animal/" + medvet.getCrmv() + "/" + id_animal;
        }

        // Caso o animal ou veterinário não sejam encontrados, volta ao formulário com uma mensagem de erro
        model.addAttribute("error", "Animal ou Veterinário não encontrados.");
        return "animais/exame_form";
    }

    @GetMapping("teste")
    public String getMethodName() {
        return "animais/teste";
    }
    
}
