package com.sge.service.categoria;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {
    Boolean existsById(Long id);

    Categoria findById(Long id);

    Page<Categoria> findAll(Pageable pageable);

    Page<Categoria> findAllByNome(String nome, Pageable pageable);

    Categoria saveCategoria(Categoria categoria) throws BadResourceException, ResourceAlreadyExistsException;

    void updateCategoria(Categoria categoria) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
