package com.sge.repository;

import com.sge.model.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    Page<Venda> findAll(Pageable page);
}