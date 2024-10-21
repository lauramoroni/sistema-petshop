package com.petshop.petshop_system.entities;

import java.math.BigDecimal;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="tb_item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_item")
    private Long id;

    @Column(nullable=false)
    private String nome;

    private String descrição;

    private int quantidade;

    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name="cpf")
    private Cliente cliente;
}
