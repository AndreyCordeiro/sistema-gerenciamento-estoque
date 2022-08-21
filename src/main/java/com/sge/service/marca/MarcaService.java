package com.sge.service.marca;

import com.sge.model.entity.Marca;

import java.util.List;

public interface MarcaService {
    List<Marca> buscarTodos();

    Marca inserir(Marca objeto);

    Marca alterar(Marca objeto);

    void excluir(Long id);
}
