package com.sge.service;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Funcionario;
import com.sge.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.module.ResolutionException;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private Boolean existsById(Long id) {
        return funcionarioRepository.existsById(id);
    }

    public Funcionario findById(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);

        if (funcionario == null) {
            throw new ResolutionException("O funcionario " + id + " não foi encontrado");
        } else {
            return funcionario;
        }
    }

    public Page<Funcionario> findAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Page<Funcionario> findAllByNome(String nome, Pageable pageable) {
        return funcionarioRepository.findByNome(nome, pageable);
    }

    public Funcionario saveFuncionario(Funcionario funcionario) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(funcionario.getNome())) {
            if (funcionario.getId() != null && existsById(funcionario.getId())) {
                throw new ResourceAlreadyExistsException("O funcionario " + funcionario.getId() + " não foi encontrado");
            }
            return funcionarioRepository.save(funcionario);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o funcionario");
            badResourceException.addErrorMessage("Funcionario está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void updateFuncionario(Funcionario funcionario) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(funcionario.getNome())) {
            if (!existsById(funcionario.getId())) {
                throw new ResourceNotFoundException("O funcionario " + funcionario.getId() + " não foi encontrado");
            }
            funcionarioRepository.save(funcionario);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o funcionario");
            badResourceException.addErrorMessage("Funcionario está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("O funcionario " + id + " não foi encontrado");
        } else {
            funcionarioRepository.deleteById(id);
        }
    }

    public Long count() {
        return funcionarioRepository.count();
    }
}
