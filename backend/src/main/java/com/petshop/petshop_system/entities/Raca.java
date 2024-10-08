package com.petshop.petshop_system.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="tb_raca")
@NoArgsConstructor
@AllArgsConstructor
public class Raca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_raca")
    private UUID id;  //posteriormente devemos definir um padrão de id para a raça
    
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_especie", nullable=false)
    private Especie especie;
}
