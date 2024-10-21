package com.petshop.petshop_system.entities;

import com.petshop.petshop_system.entities.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_itemcomprado")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemComprado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    private String nome;

    private BigDecimal preco;

    // Getters e setters
}
