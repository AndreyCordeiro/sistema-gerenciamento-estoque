package com.sge.service.cliente;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    Boolean existsById(Long id);

    Pessoa findById(Long id);

    Page<Pessoa> findAll(Pageable pageable);

    Page<Pessoa> findAllByNome(String nome, Pageable pageable);

    Pessoa saveCliente(Pessoa pessoa) throws BadResourceException, ResourceAlreadyExistsException;

    void updateCliente(Pessoa pessoa) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
