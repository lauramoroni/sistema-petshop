package com.petshop.petshop_system.controller;

import java.util.List;

import com.petshop.petshop_system.entities.VwClienteInfo;
import com.petshop.petshop_system.services.ClienteService;
import com.petshop.petshop_system.services.VwClienteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Cliente;
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
    @Autowired
    VwClienteInfoService vwClienteInfoService;
    @Autowired
    ClienteService clienteService;

    // Página de login veterinário (quem cadastra é o gerente)
    @GetMapping("/login")
    public String formularioLogin() {
        return "/medvet/login";
    }

    @PostMapping("/login")
    public String loginVeterinario(@RequestParam String crmv, @RequestParam String senha, Model model, RedirectAttributes redirectAttributes) {
        MedVet veterinario = medVetService.login(crmv, senha); // Método de validação de login

        if (veterinario != null) {
            return "redirect:/veterinario/" + crmv; // Redireciona para a página do veterinário com a lista dos animais
        } else {
            redirectAttributes.addFlashAttribute("error", "CRMV ou senha inválidos");
            return "redirect:/veterinario/login"; // Retorna para o login com erro
        }
    }

    @GetMapping("/{crmv}")
    public String homePage(@PathVariable String crmv, Model model) {
        List<VwClienteInfo> clienteInfos = vwClienteInfoService.findAll();

        model.addAttribute("clienteInfos", clienteInfos);
        return "medvet/home_medvet";

    }

    // Método para processar o formulário e cadastrar o animal
    @PostMapping("/{crmv}/{id_cliente}")
    public String insertAnimal(@PathVariable String crmv, @PathVariable String id_cliente, Animal animal) {
        // Buscar o veterinário pelo CRMV
        MedVet veterinario = medVetService.FindByCRMV(crmv);
        if (veterinario == null) {
            return "redirect:/error";
        }
        animal.setMedVet(veterinario);

        // Buscar o cliente pelo ID do cliente
        Cliente cliente = clienteService.findByCPF(id_cliente);
        if (cliente == null) {
            return "redirect:/error";
        }
        animal.setCliente(cliente);

        animalService.insert(animal);

        // Redireciona para a lista de animais do veterinário
        return "redirect:/veterinario/" + crmv;
    }

    @GetMapping("/{crmv}/cliente/{id_cliente}/animais")
    public String listAnimais(@PathVariable String crmv, @PathVariable String id_cliente, Model model) {
        Cliente cliente = clienteService.findByCPF(id_cliente);

        // Verifique se o cliente foi encontrado
        if (cliente == null) {
            return "redirect:/error";
        }

        // Buscar os animais associados ao cliente
        List<Animal> animais = animalService.findByCliente(cliente);

        model.addAttribute("animais", animais);
        model.addAttribute("cliente", cliente);
        model.addAttribute("crmv", crmv); 

        return "medvet/list_animal_client";
    }
}
