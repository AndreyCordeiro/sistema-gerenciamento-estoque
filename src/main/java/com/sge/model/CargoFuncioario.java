package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cargo_funcionario")
@Data
public class CargoFuncionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_inclusao")
    private Date dataInclusao = new Date();

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Cargo cargo;
}
