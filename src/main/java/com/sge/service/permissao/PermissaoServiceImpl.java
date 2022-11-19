package com.sge.service.permissao;

import com.sge.entity.Permissao;
import com.sge.entity.PermissaoUsuario;
import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;
import com.sge.repository.PermissaoRepository;
import com.sge.repository.PermissaoUsuarioRepository;
import com.sge.util.Util;
import com.sge.util.UtilPermissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoServiceImpl implements PermissaoService {
    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;

    @Override
    public List<Permissao> buscarTodos() {
        return permissaoRepository.findAll();
    }

    @Override
    public Permissao inserir(Permissao permissao) throws InfoException {
        if (UtilPermissao.validarPermissao(permissao)) {
            permissao.setNome(Util.removerAcentos(permissao.getNome().toUpperCase()));

            return permissaoRepository.save(permissao);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar permissão", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Permissao alterar(Long id, Permissao permissao) throws InfoException {
        Optional<Permissao> permissaoOptional = permissaoRepository.findById(id);

        if (permissaoOptional.isPresent()) {
            Permissao permissaoBuilder = Permissao.builder()
                    .id(id)
                    .nome(permissao.getNome() != null ? permissao.getNome() : null)
                    .build();

            if (UtilPermissao.validarPermissao(permissaoBuilder)) {
                permissaoBuilder.setNome(Util.removerAcentos(permissao.getNome().toUpperCase()));
                permissaoRepository.save(permissaoBuilder);
            }
            return permissaoBuilder;
        } else {
            throw new InfoException("Permissão não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void excluir(Long id) throws InfoException {
        Optional<Permissao> permissao = permissaoRepository.findById(id);

        if (permissao.isPresent()) {
            permissaoRepository.delete(permissao.get());
        } else {
            throw new InfoException("Permissão não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    public void vincularPessoaPermissaoCliente(Usuario usuario) throws InfoException {
        List<Permissao> listaPermissao = permissaoRepository.findByNome("CLIENTE");

        if (listaPermissao != null && listaPermissao.size() > 0) {
            PermissaoUsuario permissaoUsuario = new PermissaoUsuario();

            permissaoUsuario.setUsuario(usuario);
            permissaoUsuario.setPermissao(listaPermissao.get(0));
            permissaoUsuarioRepository.save(permissaoUsuario);
        } else {
            throw new InfoException("Permissão não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
