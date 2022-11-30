package com.sge.service.usuario;

import com.sge.dto.UsuarioDTO;
import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> buscarTodos();

    UsuarioDTO inserir(Usuario usuario) throws InfoException;

    Usuario alterar(Long id, Usuario usuario) throws InfoException;

    void excluir(Long id) throws InfoException;
}
