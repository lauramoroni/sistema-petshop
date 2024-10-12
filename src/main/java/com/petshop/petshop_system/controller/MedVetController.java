package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petshop.petshop_system.services.MedVetService;

@Controller
@RequestMapping("/veterinario")
public class MedVetController {

    @Autowired
    MedVetService medVetService;

    // Página de login veterinário (quem cadastra é o gerente)
    @GetMapping("/login")
    public String formularioLogin() {
        return "/medvet/login";
    }

}
