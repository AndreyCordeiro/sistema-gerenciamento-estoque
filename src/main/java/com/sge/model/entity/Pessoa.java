package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
public class Pessoa extends Auditavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cep")
    private String cep;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;
}