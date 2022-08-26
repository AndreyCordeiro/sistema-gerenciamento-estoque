package com.sge.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Produto extends Auditavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao_curta")
    private String descricaoCurta;

    @Column(name = "descricao_detalhada")
    private String descricaoDetalhada;

    @Column(name = "valor_custo")
    private Double valorCusto;

    @Column(name = "valor_venda")
    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}