package com.petshop.petshop_system.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
@AllArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pessoa")
    private UUID id;

    @Column(length = 50, nullable=false)
    private String nome;

    @Column(length = 50)
    private String nacionalidade;

    @Column(length = 9)
    private String celular;

    @Column(length = 9)
    private String telefone;

    @Column(length = 50)
    private String email;

    @Column(nullable=false)
    private LocalDateTime data_cadastro;

    @OneToOne
    @JoinColumn(name= "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Animal> animais;


    public Pessoa(){
        this.data_cadastro = LocalDateTime.now();
        this.animais = new ArrayList<>();
    }

    public Pessoa(UUID id, String nome, String nacionalidade, String celular, String telefone, String email,
            LocalDateTime data_cadastro, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.data_cadastro = LocalDateTime.now();
        this.endereco = endereco;
        this.animais = new ArrayList<>();
    }
}
