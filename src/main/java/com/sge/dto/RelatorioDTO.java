package com.sge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioDTO {
    private String nomeProduto;
    private Double quantidade;
    private Double valorUnitario;
    private Long produtoId;
    private Long vendaId;
}
