package com.webstore.repository;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_venda")
    private Double valorVenda;

    @ManyToOne
    private Fabricante fabricante;

    @ManyToOne
    private Categoria categoria;

    @Column(name = "quantidade")
    private Double quantidade;
}