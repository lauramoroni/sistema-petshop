package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Hemograma;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.services.AnimalService;
import com.petshop.petshop_system.services.HemogramaService;
import com.petshop.petshop_system.services.MedVetService;


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
    @GetMapping("/{crmv}/{id_animal}")
    public String formularioCadastro(@PathVariable Long id_animal, @PathVariable String crmv, Model model) {

        Animal animal = animalService.findById(id_animal);
        MedVet medVet = medVetService.FindByCRMV(crmv);

        model.addAttribute("animal", animal);
        model.addAttribute("medVet", medVet);
        model.addAttribute("hemograma", new Hemograma());

        return "animais/exame_form";
    }

    // Método para processar o formulário do hemograma
    @PostMapping("/{crmv}/{id_animal}")
    public String salvarExame(@PathVariable Long id_animal, @ModelAttribute("hemograma") Hemograma hemograma,
            @PathVariable String crmv, Model model) {

        Animal animal = animalService.findById(id_animal);
        MedVet medvet = medVetService.FindByCRMV(crmv);

        if (animal != null && medvet != null) {
            // Associa o hemograma ao animal e ao veterinário
            hemograma.setAnimal(animal);
            hemograma.setMedVet(medvet);

            hemogramaService.insert(hemograma);

            // Adiciona o hemograma à lista de hemogramas do animal
            animal.getHemogramas().add(hemograma);

            // Redireciona para a página de detalhes do animal
            return "redirect:/animal/" + medvet.getCrmv() + "/" + id_animal;
        }

        // Caso o animal ou veterinário não sejam encontrados, volta ao formulário com
        // uma mensagem de erro
        model.addAttribute("error", "Animal ou Veterinário não encontrados.");
        return "animais/exame_form";
    }

    @PostMapping("/{id_hemograma}")
    public String exportPdf(@PathVariable("id_hemograma") Long id_hemograma, @RequestParam Long id_animal, @RequestParam String crmv, Model model) {

        Hemograma hemograma = hemogramaService.findById(id_hemograma);
        Animal animal = animalService.findById(id_animal);
        MedVet medvet = medVetService.FindByCRMV(crmv);

        Cliente cliente = animal.getCliente();

        model.addAttribute("hemograma", hemograma);
        model.addAttribute("animal", animal);
        model.addAttribute("medvet", medvet);
        model.addAttribute("cliente", cliente);

        return "animais/pdf_exame";
    }
}
