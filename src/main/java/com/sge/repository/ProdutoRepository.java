package com.sge.repository;

import com.sge.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "select a from Produto a where a.nome like %?1%")

    Page<Produto> findByNome (String nome, Pageable page);
    Page<Produto> findAll(Pageable page);
}