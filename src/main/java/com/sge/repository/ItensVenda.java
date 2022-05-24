package com.sge.repository;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "itens_venda")
@Data
public class ItensVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantidade")
    private Double quantidade;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    @ManyToOne
    private Venda venda;

    @ManyToOne
    private Produto produto;
}
