package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // Página de cadastro do cliente
    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());  // Instancia um novo cliente
        return "/cliente/cliente_form";
    }

    // Página de login do usuário
    @GetMapping("/login")
    public ModelAndView formularioLogin() {
        ModelAndView mvLogin = new ModelAndView("/cliente/login");
        return mvLogin;
    }

    // Salva um novo cliente
    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.salvarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/cliente/cadastro";
    }

    // Página para listar clientes
    @GetMapping("/listar")
    @ResponseBody
    public List<Cliente> listarClientes() {
       return clienteService.findAll();
    }
}
