package com.sge.service.categoria;

import com.sge.exceptions.InfoException;
import com.sge.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> buscarTodos();

    Categoria inserir(Categoria objeto) throws InfoException;

    Categoria alterar(Long id, Categoria objeto) throws InfoException;

    void excluir(Long id) throws InfoException;
}
