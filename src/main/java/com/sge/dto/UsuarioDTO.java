package com.sge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String nome;
    private String documento;
    private String endereco;
    private String cep;
    private String tipoUsuario;
    private String email;
}
