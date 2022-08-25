package com.sge.service.marca;

import com.sge.exceptions.InfoException;
import com.sge.model.entity.Marca;

import java.util.List;

public interface MarcaService {
    List<Marca> buscarTodos();

    Marca inserir(Marca objeto) throws InfoException;

    Marca alterar(Long id, Marca objeto) throws InfoException;

    void excluir(Long id) throws InfoException;
}
