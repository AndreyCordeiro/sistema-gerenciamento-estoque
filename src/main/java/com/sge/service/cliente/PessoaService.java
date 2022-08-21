package com.sge.service.cliente;

import com.sge.model.entity.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> buscarTodos();

    Pessoa inserir(Pessoa objeto);

    Pessoa alterar(Pessoa objeto);

    void excluir(Long id);
}
