package com.sge.service.categoria;

import com.sge.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> buscarTodos();

    Categoria inserir(Categoria objeto);

    Categoria alterar(Categoria objeto);

    void excluir(Long id);
}
