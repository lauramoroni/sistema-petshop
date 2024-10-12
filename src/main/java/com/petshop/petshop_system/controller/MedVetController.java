package com.petshop.petshop_system.controller;

import java.util.List;

import com.petshop.petshop_system.entities.VwClienteInfo;
import com.petshop.petshop_system.services.ClienteService;
import com.petshop.petshop_system.services.VwClienteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // Página de login veterinário (quem cadastra é o gerente)
    @GetMapping("/login")
    public String formularioLogin() {
        return "medvet/login";
    }

    @PostMapping("/teste")
    public String loginVeterinario(@RequestParam String crmv, @RequestParam String senha, Model model) {
        MedVet veterinario = medVetService.login(crmv, senha); // Método de validação de login

        if (veterinario != null) {
            return "redirect:/veterinario/" + crmv; // Redireciona para a página do veterinário com a lista dos animais
        } else {
            model.addAttribute("error", "CRMV ou senha inválidos");
            return "medvet/login"; // Retorna para o login com erro
        }
    }

    /* Página de listagem dos animais que o veterinário atende
    @GetMapping("/{crmv}")
    public String listAnimal(@PathVariable String crmv, Model model) {
        MedVet veterinario = medVetService.FindByCRMV(crmv);

        List<Animal> animais = animalService.findByMedVet(veterinario);

        // Passa os dados dos animais para a view
        model.addAttribute("animais", animais);
        return "medvet/list_animal";
    }*/

    @GetMapping("/{crmv}")
    public String homePage(@PathVariable String crmv, Model model) {
       // MedVet veterinario = medVetService.FindByCRMV(crmv);

        List<VwClienteInfo> clienteInfos = vwClienteInfoService.findAll();

        // Passa os dados dos animais para a view
        model.addAttribute("clienteInfos", clienteInfos);
        return "medvet/home_medvet";

    }

    // Pagina de cadastro de animal
    @GetMapping("/{crmv}/{id_cliente}")
    public String formularioAnimal(@PathVariable String crmv, @PathVariable Long id_cliente, Model model) {
        model.addAttribute("crmv", crmv);
        model.addAttribute("id_cliente", id_cliente);
        return "/medvet/form_animal";
    }

    // Método para processar o formulário e cadastrar o animal
    @PostMapping("/{crmv}/{id_cliente}")
    public String insertAnimal(@PathVariable String crmv, @PathVariable String cpf, Animal animal) {
        // Associar o veterinário e o cliente ao animal
        MedVet veterinario = medVetService.FindByCRMV(crmv);
        animal.setMedVet(veterinario);

        Cliente cliente = new Cliente(); // Adicionar a lógica de busca do cliente usando o id_cliente
        cliente.setCpf(cpf);;
        animal.setCliente(cliente);

        // Salvar o novo animal
        animalService.insert(animal);

        // Redireciona para a lista de animais do veterinário
        return "redirect:/veterinario/" + crmv;
    }
}
