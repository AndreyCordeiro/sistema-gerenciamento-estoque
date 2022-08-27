package com.sge.service.usuario;

import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;

import java.util.List;

public interface UsuarioService {
    List<Usuario> buscarTodos();

    Usuario inserir(Usuario usuario, String tipoUsuario) throws InfoException;

    Usuario alterar(Long id, Usuario usuario, String tipoUsuario) throws InfoException;

    void excluir(Long id) throws InfoException;
}
