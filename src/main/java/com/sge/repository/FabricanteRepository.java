package com.sge.repository;

import com.sge.model.entity.Fabricante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    @Query(value = "select a from Fabricante a where a.nome like %?1%")
    Page<Fabricante> findByNome(String nome, Pageable page);

    Page<Fabricante> findAll(Pageable page);
}
