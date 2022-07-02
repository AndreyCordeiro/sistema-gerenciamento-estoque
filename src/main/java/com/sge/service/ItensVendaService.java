package com.sge.service;

import com.sge.model.entity.ItensVenda;
import com.sge.repository.ItensVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItensVendaService {
    @Autowired
    private ItensVendaRepository itensVendaRepository;

    protected ItensVenda salvarItensVenda(ItensVenda itensVenda) {
        return itensVendaRepository.save(itensVenda);
    }
}