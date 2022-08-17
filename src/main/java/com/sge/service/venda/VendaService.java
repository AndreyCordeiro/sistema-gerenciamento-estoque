package com.sge.service.venda;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendaService {
    Boolean existsById(Long id);

    Venda findById(Long id);

    Page<Venda> findAll(Pageable pageable);

    Venda salvarVenda(Venda venda) throws BadResourceException, ResourceAlreadyExistsException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
