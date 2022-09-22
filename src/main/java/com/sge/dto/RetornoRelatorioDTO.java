package com.sge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetornoRelatorioDTO {
    private String mensagem;
    private UsuarioDTO usuarioDTO;
    private ClienteDTO clienteDTO;
    private List<RelatorioDTO> relatorioDTO;
    private Double valorTotalVenda;
}
