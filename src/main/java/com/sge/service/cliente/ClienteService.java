package com.sge.service.cliente;

import com.sge.entity.Cliente;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface ClienteService {
    List<Cliente> buscarTodos();

    Cliente inserir(Cliente cliente) throws InfoException;

    Cliente alterar(Long id, Cliente cliente) throws InfoException;

    void excluir(Long id) throws InfoException;
}
