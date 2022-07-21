package com.sge.dto;

import com.sge.model.entity.Venda;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

@Data
public class VendaDTO {
    private String nome;
    private String usuario;
    private String cpf;
    private String telefone;
    private String email;

    public VendaDTO convert(Venda venda) {
        VendaDTO vendaDTO = new VendaDTO();
        BeanUtils.copyProperties(venda, vendaDTO);
        return vendaDTO;
    }

    public Page<VendaDTO> convertVenda(Page<Venda> pageVenda) {
        return pageVenda.map(this::convert);
    }
}