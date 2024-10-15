package com.petshop.petshop_system.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "tb_internacao")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_internacao")
    private Long idInternacao;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)  // Coluna de chave estrangeira que referencia a tabela animal
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "crmv", nullable = false)  // Coluna de chave estrangeira que referencia a tabela veterinario
    private MedVet medVet;

    @Column(nullable = false)
    private LocalDateTime data_internacao;

    @Column
    private String complicacao;

    @Column
    private String nivel_urgencia;
}
