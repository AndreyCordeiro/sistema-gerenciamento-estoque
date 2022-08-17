package com.sge.service.itensVenda;

import com.sge.model.entity.ItensVenda;
import com.sge.repository.ItensVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItensVendaServiceImpl{
    @Autowired
    private ItensVendaRepository itensVendaRepository;

    protected ItensVenda salvarItensVenda(ItensVenda itensVenda) {
        return itensVendaRepository.save(itensVenda);
    }
}