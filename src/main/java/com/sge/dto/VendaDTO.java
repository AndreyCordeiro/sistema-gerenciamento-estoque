package com.sge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaDTO {
    private UsuarioDTO usuario;
    private ClienteDTO cliente;
    private ItensVendaDTO itensVenda;
}
