package com.sge.service.permissao;

import com.sge.entity.Permissao;
import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface PermissaoService {
    List<Permissao> buscarTodos();

    Permissao inserir(Permissao permissao) throws InfoException;

    Permissao alterar(Long id, Permissao permissao) throws InfoException;

    void excluir(Long id) throws InfoException;

    void vincularPessoaPermissaoCliente(Usuario usuario) throws InfoException;
}
