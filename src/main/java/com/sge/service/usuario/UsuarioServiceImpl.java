package com.sge.service.usuario;

import com.sge.config.AppConfig;
import com.sge.entity.Usuario;
import com.sge.enums.TipoUsuario;
import com.sge.exceptions.InfoException;
import com.sge.repository.UsuarioRepository;
import com.sge.util.Util;
import com.sge.util.UtilPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario inserir(Usuario usuario, String tipoUsuario) throws InfoException {
        if (tipoUsuario == null || tipoUsuario.equals("")) {
            throw new InfoException("MESSAGE.TIPO_USUARIO_REQUIRED", HttpStatus.BAD_REQUEST);
        }

        if (UtilPessoa.validarPessoa(usuario)) {
            usuario.setTipoUsuario(TipoUsuario.fromValue(Util.removerAcentos(tipoUsuario)));
            usuario.setSenha(AppConfig.passwordEncoder().encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar usuário", HttpStatus.BAD_REQUEST);
        }
    }

    public Usuario alterar(Long id, Usuario usuario, String tipoUsuario) throws InfoException {
        if (tipoUsuario == null || tipoUsuario.equals("")) {
            throw new InfoException("MESSAGE.TIPO_USUARIO_REQUIRED", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalPessoa = usuarioRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Usuario usuarioBuilder = Usuario.builder()
                    .id(id)
                    .nome(usuario.getNome() != null ? usuario.getNome() : null)
                    .documento(usuario.getDocumento() != null ? usuario.getDocumento() : null)
                    .endereco(usuario.getEndereco() != null ? usuario.getEndereco() : null)
                    .cep(usuario.getCep() != null ? usuario.getCep() : null)
                    .email(usuario.getEmail() != null ? usuario.getEmail() : null)
                    .tipoUsuario(TipoUsuario.fromValue(Util.removerAcentos(tipoUsuario)))
                    .senha(usuario.getSenha() != null ? AppConfig.passwordEncoder().encode(usuario.getSenha()) : null)
                    .build();

            if (UtilPessoa.validarPessoa(usuarioBuilder)) {
                usuarioRepository.save(usuarioBuilder);
            }
            return usuarioBuilder;
        } else {
            throw new InfoException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Usuario> pessoa = usuarioRepository.findById(id);

        if (pessoa.isPresent()) {
            usuarioRepository.delete(pessoa.get());
        } else {
            throw new InfoException("Usuário não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
