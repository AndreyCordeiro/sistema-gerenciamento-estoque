package com.sge.service.produto;

import com.sge.exceptions.InfoException;
import com.sge.model.entity.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> buscarTodos();

    Produto inserir(Produto objeto) throws InfoException;

    Produto alterar(Long id, Produto objeto) throws InfoException;

    void excluir(Long id) throws InfoException;
}
