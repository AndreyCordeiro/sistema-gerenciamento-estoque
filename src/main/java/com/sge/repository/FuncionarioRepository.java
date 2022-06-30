package com.sge.repository;

import com.sge.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query(value = "select a from Funcionario a where a.nome like %?1%")
    Page<Funcionario> findByNome(String nome, Pageable page);
    Page<Funcionario> findAll(Pageable page);
}