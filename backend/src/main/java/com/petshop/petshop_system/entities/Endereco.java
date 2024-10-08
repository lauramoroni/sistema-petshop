package com.petshop.petshop_system.entities;

import java.util.UUID;

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
@Table(name = "tb_endereco")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_endereco")
    private UUID id;

    @Column(length = 8)
    private String cep;
    
    private String rua;
    private String bairro;
    private String complemento;
    private String ponto_referencia;
    private String cidade;
    private String estado;
    private int numero;

    
}
