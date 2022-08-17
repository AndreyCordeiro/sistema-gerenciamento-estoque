package com.sge.service.fabricante;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Fabricante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FabricanteService {
    Boolean existsById(Long id);

    Fabricante findById(Long id);

    Page<Fabricante> findAll(Pageable pageable);

    Page<Fabricante> findAllByNome(String nome, Pageable pageable);

    Fabricante saveFabricante(Fabricante fabricante) throws BadResourceException, ResourceAlreadyExistsException;

    void updateFabricante(Fabricante fabricante) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;
}
