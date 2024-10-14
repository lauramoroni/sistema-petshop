package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petshop.petshop_system.entities.Hemograma;
import com.petshop.petshop_system.services.HemogramaService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/exame")
public class HemogramaController {

    @Autowired
    HemogramaService HemogramaService;

    // Página de cadastro do cliente
    @GetMapping("/adicionar")
    public String formularioCadastro(Model model) {
        model.addAttribute("exame", new Hemograma());  // Instancia um novo cliente
        return "/animais/exame_form";
    }

    

}
