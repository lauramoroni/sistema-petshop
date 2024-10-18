package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Gerente;
import com.petshop.petshop_system.entities.Item;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.services.ClienteService;
import com.petshop.petshop_system.services.ItemService;
import com.petshop.petshop_system.services.MedVetService;

import org.springframework.ui.Model;
import com.petshop.petshop_system.services.GerenteService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private MedVetService medVetService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/login")
    public String login() {
        return "/gerente/login";
    }


    @PostMapping("/login")
    public String logar(Model model, @RequestParam String login, @RequestParam String senha) {
        Gerente gerente = gerenteService.findByLogin(login);
        // Criar lógica para o login
        if (gerente != null) {
            return "redirect:/gerente/home"; // Redireciona para a página do veterinário com a lista dos animais
        } else {
            model.addAttribute("error", "Login ou senha inválidos");
            return "redirect:/gerente/login"; // Retorna para o login com erro
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("itemList", itemService.findAll());
        model.addAttribute("veterinarioList", medVetService.findAll());
        model.addAttribute("clienteList", clienteService.findAll());
        return "/gerente/home_gerente";
    }

   @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("veterinario", new MedVet());
        return "/gerente/veterinario_form";
    }

    @PostMapping("/cadastro")
    public String salvarMedVet(@ModelAttribute MedVet MedVet) {
        medVetService.insert(MedVet);
        return "redirect:/gerente/home";
    }

    @GetMapping("/atualizar")
    public String atualizar(@RequestParam String crmv, Model model) {
        model.addAttribute("veterinario", medVetService.FindByCRMV(crmv));
        return "/gerente/veterinario_update";
    }

    @PostMapping("/atualizar")
    public String atualizarMedVet(@ModelAttribute MedVet MedVet, @RequestParam String crmv) {
        medVetService.update(MedVet,crmv);
        return "redirect:/gerente/home";

    }

    @PostMapping("/deletar")
    public String deletarMedVet(@RequestParam String crmv) {
        medVetService.delete(crmv);
        return "redirect:/gerente/home";
    }






}
