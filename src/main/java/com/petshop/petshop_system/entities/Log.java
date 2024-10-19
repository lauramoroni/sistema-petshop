package com.petshop.petshop_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_log")
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    private String cpf;

    private LocalDateTime date;

    private String cliente;

    private String modificacao;

};
