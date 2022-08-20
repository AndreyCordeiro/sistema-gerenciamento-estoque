package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "fabricante")
@Data
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;
}