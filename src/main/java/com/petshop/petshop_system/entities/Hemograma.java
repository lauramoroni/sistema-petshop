package com.petshop.petshop_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_hemograma")
public class Hemograma {

    @Id
    @Column(name = "id_hemograma")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id_hemograma;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @Column(nullable = false)
    public double hemacias;

    @Column(nullable = false)
    public double hematocrito;

    @Column(nullable = false)
    public double hemoglobina;

    @Column(nullable = false)
    public double leucocitos;

    @Column(nullable = false)
    public double linfocitos;

    @Column(nullable = false)
    public double plaquetas;

    @Column
    public String observacoes;
    
}
