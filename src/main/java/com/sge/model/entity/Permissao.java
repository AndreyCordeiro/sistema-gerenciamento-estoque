package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permissao")
@Data
public class Permissao extends Auditavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;
}
