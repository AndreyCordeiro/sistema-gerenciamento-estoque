package com.sge.service.cidade;

import com.sge.entity.Cidade;

import java.util.List;

public interface CidadeService {
    List<Cidade> buscarTodos();

    Cidade inserir(Cidade objeto);

    Cidade alterar(Cidade objeto);

    void excluir(Long id);
}
