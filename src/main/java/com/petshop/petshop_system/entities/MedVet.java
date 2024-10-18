package com.petshop.petshop_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_medvet")
public class MedVet extends Pessoa{

    @Column(unique = true, nullable = false, length = 11)
    private String crmv;

    @Column(nullable = false)
    private String especialidade;

    @Builder
    public MedVet(String cpf, String nome, String celular, String telefone, String email, Endereco endereco, String crmv, String especialidade, String login, String senha) {
        super(cpf, nome, celular, telefone, email, endereco, login, senha);
        this.crmv = crmv;
        this.especialidade = especialidade;
    }

}
