package com.petshop.petshop_system.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(length = 100)
    private String profissao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Animal> animais;

    // Construtor que chama o construtor da classe Pessoa
    public Cliente(String nome, String celular, String telefone, String email, Endereco endereco, String cpf, String login, String senha) {
        super(cpf, nome, celular, telefone, email, endereco, login, senha);
        this.profissao = profissao;
        this.animais = new ArrayList<>();
    }
}


