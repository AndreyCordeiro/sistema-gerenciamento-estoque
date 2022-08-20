package com.sge.dto;

import com.sge.model.entity.Pessoa;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

@Data
public class ClienteDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String email;

    public ClienteDTO convert(Pessoa pessoa) {
        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(pessoa, clienteDTO);
        return clienteDTO;
    }

    public Page<ClienteDTO> convertCliente(Page<Pessoa> pageCliente) {
        return pageCliente.map(this::convert);
    }
}