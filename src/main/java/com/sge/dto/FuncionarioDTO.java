package com.sge.dto;

import com.sge.model.Funcionario;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

@Data
public class FuncionarioDTO {
    private String nome;
    private String usuario;
    private String cpf;
    private String telefone;
    private String email;

    public FuncionarioDTO convert(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        BeanUtils.copyProperties(funcionario, funcionarioDTO);
        return funcionarioDTO;
    }

    public Page<FuncionarioDTO> convertCliente(Page<Funcionario> pageFuncionario) {
        Page<FuncionarioDTO> funcionarioDTO = pageFuncionario.map(this::convert);
        return funcionarioDTO;
    }
}