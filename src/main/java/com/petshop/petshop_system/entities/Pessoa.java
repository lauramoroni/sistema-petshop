package com.petshop.petshop_system.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@DiscriminatorColumn(name = "tipo")
@NoArgsConstructor
public abstract class Pessoa {

    @Id
    private String cpf;

    @Column(length = 50, nullable=false)
    private String nome;

    @Column(length = 9)
    private String celular;

    @Column(length = 9)
    private String telefone;

    @Column(length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id_endereco")
    private Endereco endereco;

    @Column(length = 20)
    private String login;

    @Column(length = 20)
    private String senha;




    public Pessoa(String cpf, String nome, String celular, String telefone, String email, Endereco endereco, String login, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.login = login;
        this.senha = senha;
    }
}
