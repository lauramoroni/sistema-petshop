package com.petshop.petshop_system.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@NoArgsConstructor
@Immutable
@Table(name = "vw_cliente_info")
public class VwClienteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String celular;
    private String email;

    
}

