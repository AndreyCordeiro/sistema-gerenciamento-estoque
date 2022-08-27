package com.sge.service.pessoa;

import com.sge.config.AppConfig;
import com.sge.entity.Pessoa;
import com.sge.exceptions.InfoException;
import com.sge.repository.PessoaRepository;
import com.sge.util.UtilPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa inserir(Pessoa pessoa) throws InfoException {
        if (UtilPessoa.validarPessoa(pessoa)) {
            pessoa.setSenha(AppConfig.passwordEncoder().encode(pessoa.getSenha()));
            return pessoaRepository.save(pessoa);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar pessoa", HttpStatus.BAD_REQUEST);
        }
    }

    public Pessoa alterar(Long id, Pessoa pessoa) throws InfoException {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Pessoa pessoaBuilder = Pessoa.builder()
                    .id(id)
                    .nome(pessoa.getNome() != null ? pessoa.getNome() : null)
                    .cpf(pessoa.getCpf() != null ? pessoa.getCpf() : null)
                    .endereco(pessoa.getEndereco() != null ? pessoa.getEndereco() : null)
                    .cep(pessoa.getCep() != null ? pessoa.getCep() : null)
                    .email(pessoa.getEmail() != null ? pessoa.getEmail() : null)
                    .senha(pessoa.getSenha() != null ? AppConfig.passwordEncoder().encode(pessoa.getSenha()) : null)
                    .build();

            if (UtilPessoa.validarPessoa(pessoaBuilder)) {
                pessoaRepository.save(pessoaBuilder);
            }
            return pessoaBuilder;
        } else {
            throw new InfoException("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (pessoa.isPresent()) {
            pessoaRepository.delete(pessoa.get());
        } else {
            throw new InfoException("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
