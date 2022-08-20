package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Data
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_cargo")
    private String nome;
}