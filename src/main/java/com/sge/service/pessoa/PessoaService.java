package com.sge.service.pessoa;

import com.sge.entity.Pessoa;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface PessoaService {
    List<Pessoa> buscarTodos();

    Pessoa inserir(Pessoa pessoa) throws InfoException;

    Pessoa alterar(Long id, Pessoa pessoa) throws InfoException;

    void excluir(Long id) throws InfoException;
}
