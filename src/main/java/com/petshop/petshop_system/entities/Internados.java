package com.petshop.petshop_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Internados {

    @Id
    @Column
    private Long id_internacao;

    @Column 
    private String status;

    @Column
    private String data_internacao;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;
}
