package com.sge.service.funcionario;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioService {
    Boolean existsById(Long id);

    Funcionario findById(Long id);

    Page<Funcionario> findAll(Pageable pageable);

    Page<Funcionario> findAllByNome(String nome, Pageable pageable);

    Funcionario saveFuncionario(Funcionario funcionario) throws BadResourceException, ResourceAlreadyExistsException;

    void updateFuncionario(Funcionario funcionario) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
