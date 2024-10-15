package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Gerente;
import com.petshop.petshop_system.services.ItemService;
import org.springframework.ui.Model;
import com.petshop.petshop_system.services.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/login")
    public String login() {
        return "/gerente/login";
    }

    @PostMapping("/login")
    public String logar(Model model, @RequestParam String login, @RequestParam String senha) {
        Gerente gerente = gerenteService.findByLogin(login);
        model.addAttribute("itemList", itemService.findAll());

        // Criar lógica para o login

        return "gerente/home_gerente";

    }
}
