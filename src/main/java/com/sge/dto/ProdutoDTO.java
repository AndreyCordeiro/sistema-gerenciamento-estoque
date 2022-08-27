package com.sge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private Double valorCusto;
    private Double valorVenda;
    private FabricanteDTO fabricante;
    private CategoriaDTO categoria;
}
