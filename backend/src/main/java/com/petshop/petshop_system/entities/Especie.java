package com.petshop.petshop_system.entities;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="tb_especie")
@AllArgsConstructor
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_especie")
    private UUID id;  //posteriormente devemos definir um padrão de id para a espécie
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String setor;
    
    @OneToMany(mappedBy="especie", cascade= CascadeType.ALL)
    private List<Raca> racas;

    public Especie(){
        this.racas = new ArrayList<>();
    }

    public Especie(UUID id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.racas = new ArrayList<>();
    }



}
