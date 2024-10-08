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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//sugestão para todas as classes: Validações com Jakarta Bean Validation. Validação em nível de aplicação antes de os dados serem enviados ao banco de dados. Isso ajuda a fornecer feedback ao usuário sobre entradas inválidas.

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="tb_animal")
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_animal")
    private UUID id;  //posteriormente devemos definir um padrão de id para o animal

    @Column(nullable=false)
    private String nome;

    private String sexo;

    @ManyToOne
    @JoinColumn(name = "id_especie", nullable=false)
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "id_raca")
    private Raca raca;

    private String pelagem;
    private String status;
    private String Esterilizacao;

    @ManyToOne
    @JoinColumn(name="id_pessoa", nullable=false)
    private Pessoa pessoa;
}
