package com.sge.service.itensVenda;

import com.sge.entity.ItensVenda;
import com.sge.repository.ItensVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItensVendaServiceImpl implements ItensVendaService {
    @Autowired
    private ItensVendaRepository itensVendaRepository;

    @Override
    public ItensVenda inserir(ItensVenda itensVenda) {
        return itensVendaRepository.save(itensVenda);
    }
}
