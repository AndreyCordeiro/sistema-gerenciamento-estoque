package com.sge.repository;

import com.sge.model.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Pessoa, Long> {
    @Query(value = "select a from Pessoa a where a.nome like %?1%")
    Page<Pessoa> findByNome(String nome, Pageable page);
    Page<Pessoa> findAll(Pageable page);
}
