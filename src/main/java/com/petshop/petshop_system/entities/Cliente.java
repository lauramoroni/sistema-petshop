package com.petshop.petshop_system.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 100)
    private String profissao;

    // Construtor que chama o construtor da classe Pessoa
    public Cliente(UUID id, String nome, String nacionalidade, String celular, String telefone, String email,
                   LocalDateTime dataCadastro, Endereco endereco, String cpf, String profissao) {
        super(id, nome, nacionalidade, celular, telefone, email, dataCadastro, endereco);
        this.cpf = cpf;
        this.profissao = profissao;
    }
}


