package com.sge.service.venda;

import com.sge.entity.Venda;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface VendaService {
    List<Venda> buscarTodos();

    Venda inserir(Venda venda) throws InfoException;

    void excluir(Long id) throws InfoException;
}
