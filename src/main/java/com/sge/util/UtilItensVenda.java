package com.sge.util;

import com.sge.dto.ItensVendaDTO;
import com.sge.entity.ItensVenda;

import java.util.ArrayList;
import java.util.List;

public class UtilItensVenda {
    public static List<ItensVendaDTO> converterListaItensVenda(List<ItensVenda> itensVendaList) {
        List<ItensVendaDTO> itensVendaDTOList = new ArrayList<>();

        for (ItensVenda itensVenda : itensVendaList) {
            itensVendaDTOList.add(converteItensVenda(itensVenda));
        }
        return itensVendaDTOList;
    }

    public static ItensVendaDTO converteItensVenda(ItensVenda itensVenda) {
        return ItensVendaDTO.builder()
                .quantidade(itensVenda.getQuantidade())
                .valorUnitario(itensVenda.getValorUnitario())
                .produto(UtilProduto.converterProduto(itensVenda.getProduto()))
                .build();
    }
}
