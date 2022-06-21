package com.sge.repository;

import com.sge.model.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    @Query(value = "select a from Cargo a where a.nome like %?1%")
    Page<Cargo> findByNome(String nome, Pageable page);

    Page<Cargo> findAll(Pageable page);
}
