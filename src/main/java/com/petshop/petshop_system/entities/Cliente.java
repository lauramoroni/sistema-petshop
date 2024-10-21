package com.petshop.petshop_system.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Animal> animais;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ItemComprado> items;

    private String cad_unico;

    // Construtor que chama o construtor da classe Pessoa
    public Cliente(String nome, String celular, String telefone, String email, Endereco endereco, String cpf, String login, String senha, String profissao) {
        super(cpf, nome, celular, telefone, email, endereco, login, senha);
        this.animais = new ArrayList<>();
        this.items = new ArrayList<>();
    }
}


