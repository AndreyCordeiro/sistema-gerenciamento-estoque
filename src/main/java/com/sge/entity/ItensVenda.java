package com.sge.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "itens_venda")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ItensVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantidade")
    private Double quantidade;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Venda venda;

    @ManyToOne
    private Produto produto;
}
