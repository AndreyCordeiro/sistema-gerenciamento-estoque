package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_venda")
    private Date dataVenda = new Date();

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Pessoa pessoa;

    @Transient
    private List<ItensVenda> itensVenda;
}