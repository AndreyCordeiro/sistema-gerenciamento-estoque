package com.sge.service.fabricante;

import com.sge.exceptions.InfoException;
import com.sge.entity.Fabricante;

import java.util.List;

public interface FabricanteService {
    List<Fabricante> buscarTodos();

    Fabricante inserir(Fabricante objeto) throws InfoException;

    Fabricante alterar(Long id, Fabricante objeto) throws InfoException;

    void excluir(Long id) throws InfoException;
}
