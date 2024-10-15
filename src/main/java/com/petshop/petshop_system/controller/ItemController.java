package com.petshop.petshop_system.controller;

import com.petshop.petshop_system.repositories.ItemRepository;
import org.springframework.ui.Model;
import com.petshop.petshop_system.entities.Item;
import com.petshop.petshop_system.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("item", new Item());
        return "/gerente/item_form";
    }

    @PostMapping("/cadastro")
    public String salvarItem(@ModelAttribute Item item) {
        itemService.insert(item);
        return "redirect:/gerente/home";

    }

    @PostMapping("/aplicar-desconto")
    public String aplicarDesconto(@RequestParam Long itemId, @RequestParam BigDecimal percentualDesconto) {
        itemService.aplicarDesconto(itemId, percentualDesconto);
        return "redirect:/gerente/home";
    }

    @GetMapping("/atualizar")
    public String atualizar(@RequestParam Long itemId, Model model) {
        model.addAttribute("item", itemService.findById(itemId));
        return "/gerente/item_update";
    }

    @PostMapping("/atualizar")
    public String atualizarItem(@ModelAttribute Item item, @RequestParam Long itemId) {
        itemService.update(item, itemId);
        return "redirect:/gerente/home";

    }

    @PostMapping("/deletar")
    public String deletarItem(@RequestParam Long itemId) {
        itemService.delete(itemId);
        return "redirect:/gerente/home";
    }


}
