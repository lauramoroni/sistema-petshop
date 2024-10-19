package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.Item;
import com.petshop.petshop_system.entities.ItemComprado;
import com.petshop.petshop_system.repositories.ClienteRepository;
import com.petshop.petshop_system.repositories.ItemCompradoRepository;
import com.petshop.petshop_system.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ItemCompradoRepository itemCompradoRepository;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluirCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public Cliente findByCPF(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }


    public ItemComprado comprarItem(String cpf, Long itemId) {
        Cliente cliente = findByCPF(cpf);
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        ItemComprado itemComprado = new ItemComprado();
        itemComprado.setNome(item.getNome());
        itemComprado.setCliente(cliente);
        itemComprado.setPreco(item.getPreco());
        item.setQuantidade(item.getQuantidade() - 1);
        itemService.update(item, itemId);
        cliente.getItems().add(itemComprado);
        return itemCompradoRepository.save(itemComprado);
    }
}
