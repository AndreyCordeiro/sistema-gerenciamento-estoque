package com.sge.repository;

import com.sge.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findVendaByUsuarioId(Long id);
}
