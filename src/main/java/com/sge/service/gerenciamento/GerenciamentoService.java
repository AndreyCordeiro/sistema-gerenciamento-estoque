package com.sge.service.gerenciamento;

import com.sge.entity.Usuario;

public interface GerenciamentoService {
    String solicitarCodigo(String email);

    String alterarSenha(Usuario usuario);
}
