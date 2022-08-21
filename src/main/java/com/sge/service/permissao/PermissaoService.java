package com.sge.service.permissao;

import com.sge.model.entity.Permissao;

import java.util.List;

public interface PermissaoService {
    List<Permissao> buscarTodos();

    Permissao inserir(Permissao objeto);

    Permissao alterar(Permissao objeto);

    void excluir(Long id);
}
