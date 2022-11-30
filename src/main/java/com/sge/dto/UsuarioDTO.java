package com.sge.dto;

import com.sge.entity.PermissaoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String documento;
    private String endereco;
    private String cep;
    private String email;
    private List<PermissaoUsuario> permissaoUsuarios;
}
