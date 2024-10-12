package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    // Salva um novo cliente
    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.salvarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/cliente/cadastro";
    }

    // Página de login do usuário
    @GetMapping("/login")
    public String formularioLogin( ) {
        return "/cliente/login";
    }

    @GetMapping("/home")
    public String homeCliente( ) {
        return "/cliente/Home_Cliente";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String cpf,
                                 @RequestParam String senha,
                                 Model model) {
        Cliente clienteCPF = clienteService.findByCPF(cpf);


        if (clienteCPF.getProfissao() == null && clienteCPF.getAnimais().isEmpty()) {
            // Se o cliente não for encontrado
            model.addAttribute("erro", "Cliente não encontrado. Por favor, cadastre-se.");
            return "redirect:/cliente/cadastro"; // Redireciona para a página de cadastro
        } else if (clienteCPF.getSenha().equals(senha)) {
            // Se a senha estiver correta
            return "redirect:/cliente/home"; // Redireciona para a página inicial do cliente
        } else {
            // Se a senha estiver incorreta
            model.addAttribute("erro", "Senha incorreta");
            return "redirect: /cliente/login"; // Retorna para a página de login
        }
    }


    // Página para listar clientes
    @GetMapping("/listar")
    @ResponseBody
    public List<Cliente> listarClientes() {
       return clienteService.findAll();
    }
}
