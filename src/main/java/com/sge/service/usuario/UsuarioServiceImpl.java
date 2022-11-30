package com.sge.service.usuario;

import com.sge.dto.UsuarioDTO;
import com.sge.entity.PermissaoUsuario;
import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;
import com.sge.repository.PermissaoUsuarioRepository;
import com.sge.repository.UsuarioRepository;
import com.sge.util.UtilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;

    public List<UsuarioDTO> buscarTodos() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        if (listaUsuarios.size() > 0) {
            for (Usuario usuario : listaUsuarios) {
                usuarioDTOList.add(UtilUsuario.converteUsuario(usuario));
            }
        }
        return usuarioDTOList;
    }

    public UsuarioDTO inserir(Usuario usuario) throws InfoException {
        if (UtilUsuario.validarUsuario(usuario)) {
            PermissaoUsuario permissaoUsuario = new PermissaoUsuario();

            permissaoUsuario.setUsuario(usuario);
            permissaoUsuario.setPermissao(usuario.getPermissaoUsuarios().get(0).getPermissao());

            usuarioRepository.save(usuario);
            permissaoUsuarioRepository.save(permissaoUsuario);

            return UtilUsuario.converteUsuario(usuario);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar usuário", HttpStatus.BAD_REQUEST);
        }
    }

    public Usuario alterar(Long id, Usuario usuario) throws InfoException {
        Optional<Usuario> optionalPessoa = usuarioRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Usuario usuarioBuilder = Usuario.builder()
                    .id(id)
                    .nome(usuario.getNome() != null ? usuario.getNome() : null)
                    .documento(usuario.getDocumento() != null ? usuario.getDocumento() : null)
                    .endereco(usuario.getEndereco() != null ? usuario.getEndereco() : null)
                    .cep(usuario.getCep() != null ? usuario.getCep() : null)
                    .email(usuario.getEmail() != null ? usuario.getEmail() : null)
                    .permissaoUsuarios(usuario.getPermissaoUsuarios() != null && usuario.getPermissaoUsuarios().size() > 0 ? usuario.getPermissaoUsuarios() : null)
                    .build();

            if (UtilUsuario.validarUsuario(usuarioBuilder)) {
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
            throw new InfoException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public Usuario encontrarUsuarioPorId(Long id) throws InfoException {
        Optional<Usuario> pessoa = usuarioRepository.findById(id);

        if (pessoa.isPresent()) {
            return pessoa.get();
        } else {
            throw new InfoException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
