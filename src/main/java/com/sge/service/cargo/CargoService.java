package com.sge.service.cargo;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CargoService {
    Boolean existsById(Long id);

    Cargo findById(Long id);

    Page<Cargo> findAll(Pageable pageable);

    Page<Cargo> findAllByNome(String nome, Pageable pageable);

    Cargo saveCargo(Cargo cargo) throws BadResourceException, ResourceAlreadyExistsException;

    void updateCargo(Cargo cargo) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
