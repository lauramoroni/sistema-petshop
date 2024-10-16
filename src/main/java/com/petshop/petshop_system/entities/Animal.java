package com.petshop.petshop_system.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="tb_animal")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Animal {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_animal")
    private Long id; 

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private String sexo;

    @Column(nullable=false)
    private String Esterilizacao;

    @Column
    private Integer idade;

    @ManyToOne
    @JoinColumn(name="cpf")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "crmv")
    private MedVet medVet;  // Associação com o veterinário

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Hemograma> hemogramas = new ArrayList<>();

}
