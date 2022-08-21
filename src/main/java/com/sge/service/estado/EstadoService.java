package com.sge.service.estado;

import com.sge.model.entity.Estado;

import java.util.List;

public interface EstadoService {
    List<Estado> buscarTodos();

    Estado inserir(Estado objeto);

    Estado alterar(Estado objeto);

    void excluir(Long id);
}
