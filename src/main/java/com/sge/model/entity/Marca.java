package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "marca")
@Data
public class Marca extends Auditavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;
}
