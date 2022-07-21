package com.sge.dto;

import com.sge.model.entity.Funcionario;
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

    public Page<FuncionarioDTO> convertFuncionario(Page<Funcionario> pageFuncionario) {
        return pageFuncionario.map(this::convert);
    }
}