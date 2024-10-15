package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.Internacao;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.services.AnimalService;
import com.petshop.petshop_system.services.ClienteService;
import com.petshop.petshop_system.services.MedVetService;

import java.time.LocalDateTime;
import java.util.List;

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
    @PostMapping("/{crmv}/cliente/{cpf}/animais")
    public String adicionarAnimal(@ModelAttribute("animal") Animal animal, @PathVariable String cpf,
            @PathVariable String crmv) {
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
    @GetMapping("/{crmv}/{id_animal}")
    public String detalhesAnimal(@PathVariable Long id_animal, @PathVariable String crmv, Model model) {
        Animal animal = animalService.findById(id_animal);

        // Verifica se o animal existe
        if (animal == null) {
            model.addAttribute("error", "Animal não encontrado.");
            return "animais/error"; // Ou alguma outra página de erro
        }

        // Adiciona o animal e a lista de hemogramas ao modelo
        model.addAttribute("animal", animal);
        model.addAttribute("hemogramas", animal.getHemogramas());

        return "animais/detalhe_animal";
    }

    // Animais internados
    @GetMapping("/uti/")
    public String animaisInternadosForm(@RequestParam Long id_animal, @RequestParam String crmv, Model model) {

        Animal animal = animalService.findById(id_animal);
        Internacao internacao = new Internacao();
        MedVet medVet = medVetService.FindByCRMV(crmv);

        internacao.setAnimal(animal);

        model.addAttribute("internacao", internacao);
        model.addAttribute("animal", animal);
        model.addAttribute("veterinarioNome", medVet.getNome());

        return "animais/internados_form";
    }

    @PostMapping("/uti/")
    public String internarAnimal(@RequestParam Long animalId, @RequestParam String crmv, Model model) {
        Animal animal = animalService.findById(animalId);
        MedVet medVet = medVetService.FindByCRMV(crmv);
        Internacao internacao = new Internacao();

        animal.setStatus("internado");
        animalService.update(animalId, animal);

        model.addAttribute("veterinarioNome", medVet.getNome()); 
        model.addAttribute("internacao", internacao);

        return "redirect:animal/uti/" + animalId; // redireciona para o formulário de internação
    }


    @PostMapping("/uti/save")
    public String salvarInternacao(@ModelAttribute Internacao internacao) {
        internacao.setData_internacao(LocalDateTime.now()); // define a data de internação

        return "redirect:/animal/uti"; // redireciona para a lista de animais internados
    }


}
