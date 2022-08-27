package com.sge.service.cliente;

import com.sge.dto.ClienteDTO;
import com.sge.entity.Cliente;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> buscarTodos();

    ClienteDTO inserir(Cliente cliente) throws InfoException;

    ClienteDTO alterar(Long id, Cliente cliente) throws InfoException;

    void excluir(Long id) throws InfoException;
}
