package com.sge.repository;

import com.sge.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "select a from Cliente a where a.nome like %?1%")
    Page<Cliente> findByNome(String nome, Pageable page);
    Page<Cliente> findAll(Pageable page);
}
