package com.sge.service.pessoa;

import com.sge.entity.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> buscarTodos();

    Pessoa inserir(Pessoa objeto);

    Pessoa alterar(Pessoa objeto);

    void excluir(Long id);
}
