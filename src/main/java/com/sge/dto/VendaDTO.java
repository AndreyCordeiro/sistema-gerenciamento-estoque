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
public class VendaDTO {
    private UsuarioDTO usuario;
    private ClienteDTO cliente;
    private List<ItensVendaDTO> itensVenda;
}
