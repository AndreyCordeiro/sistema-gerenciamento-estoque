package com.sge.service.cliente;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    Boolean existsById(Long id);

    Cliente findById(Long id);

    Page<Cliente> findAll(Pageable pageable);

    Page<Cliente> findAllByNome(String nome, Pageable pageable);

    Cliente saveCliente(Cliente cliente) throws BadResourceException, ResourceAlreadyExistsException;

    void updateCliente(Cliente cliente) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
