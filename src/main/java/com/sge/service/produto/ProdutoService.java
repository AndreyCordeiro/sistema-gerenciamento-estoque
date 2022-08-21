package com.sge.service.produto;

import com.sge.model.entity.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> buscarTodos();

    Produto inserir(Produto objeto);

    Produto alterar(Produto objeto);

    void excluir(Long id);
}
