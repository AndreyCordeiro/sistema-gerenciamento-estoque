package com.sge.service.itensVenda;

import com.sge.entity.ItensVenda;
import com.sge.exceptions.InfoException;

public interface ItensVendaService {
    ItensVenda inserirItensVenda(ItensVenda itensVenda);

    void excluirItensVenda(Long id) throws InfoException;
}
