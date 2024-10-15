package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.Item;
import com.petshop.petshop_system.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

     public void aplicarDesconto(Long itemId, BigDecimal percentualDesconto) {
        itemRepository.aplicarDesconto(itemId, percentualDesconto);
    }

    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Item item, Long id) {
        Item itemUpdated = itemRepository.findById(id).orElse(null);
        itemUpdated.setNome(item.getNome());
        itemUpdated.setDescrição(item.getDescrição());
        itemUpdated.setPreco(item.getPreco());
        itemUpdated.setQuantidade(item.getQuantidade());
        return itemRepository.save(itemUpdated);
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }
}
