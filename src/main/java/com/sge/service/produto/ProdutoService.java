package com.sge.service.produto;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {
    Produto findById(Long id);

    Page<Produto> findAll(Pageable pageable);

    Page<Produto> findAllByNome(String nome, Pageable pageable);

    Produto saveProduto(Produto produto) throws BadResourceException, ResourceAlreadyExistsException;

    void updateProduto(Produto produto) throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();
}
