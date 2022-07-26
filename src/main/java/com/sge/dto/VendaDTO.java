package com.sge.dto;

import com.sge.model.entity.Cliente;
import com.sge.model.entity.Funcionario;
import com.sge.model.entity.ItensVenda;
import com.sge.model.entity.Venda;
import lombok.Data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

@Data
public class VendaDTO {
	
	private Date dataVenda = new Date();
	private Funcionario funcionario;
	private Cliente cliente;
	private List<ItensVenda> itensVenda;

    public VendaDTO convert(Venda venda) {
        VendaDTO vendaDTO = new VendaDTO();
        BeanUtils.copyProperties(venda, vendaDTO);
        return vendaDTO;
    }

    public Page<VendaDTO> convertVenda(Page<Venda> pageVenda) {
        return pageVenda.map(this::convert);
    }
}
