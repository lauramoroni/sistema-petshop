package com.petshop.petshop_system.controller;

import org.springframework.ui.Model;
import com.petshop.petshop_system.entities.Item;
import com.petshop.petshop_system.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("item", new Item());
        return "/gerente/item_form";
    }

    @PostMapping("/cadastro")
    public String salvarItem(@ModelAttribute Item item) {
        itemService.insert(item);

        return "/gerente/home_gerente";

    }

}
