package com.sge.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Data
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_venda")
    private Date dataVenda = new Date();

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Cliente cliente;

    @Transient
    private List<ItensVenda> itensVenda;
}