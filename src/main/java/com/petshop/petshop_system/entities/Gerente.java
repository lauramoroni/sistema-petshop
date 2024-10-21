package com.petshop.petshop_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_gerente")
@AllArgsConstructor
@NoArgsConstructor
public class Gerente extends Pessoa{

    @Column(unique = true, nullable = false, length = 11)
    private String login;

    @Builder
    public Gerente(String cpf, String nome, String celular, String telefone, String email, Endereco endereco, String login, String senha) {
        super(cpf, nome, celular, telefone, email, endereco, login, senha);
        this.login = login;
    }
}
