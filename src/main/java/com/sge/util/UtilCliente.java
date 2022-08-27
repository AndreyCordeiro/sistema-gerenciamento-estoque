package com.sge.util;

import com.sge.dto.ClienteDTO;
import com.sge.entity.Cliente;
import com.sge.exceptions.InfoException;
import org.springframework.http.HttpStatus;

public class UtilCliente {
    public static Boolean validarCliente(Cliente cliente) throws InfoException {
        if (cliente.getNome() == null || cliente.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getDocumento() == null || cliente.getDocumento().equals("")) {
            throw new InfoException("MESSAGE.DOCUMENTO_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().equals("")) {
            throw new InfoException("MESSAGE.ENDERECO_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getCep() == null || cliente.getCep().equals("")) {
            throw new InfoException("MESSAGE.CEP_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getEmail() == null || cliente.getEmail().equals("")) {
            throw new InfoException("MESSAGE.EMAIL_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getSenha() == null || cliente.getSenha().equals("")) {
            throw new InfoException("MESSAGE.SENHA_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }

    public static ClienteDTO converteCliente(Cliente cliente) {
        return ClienteDTO.builder()
                .nome(cliente.getNome())
                .documento(cliente.getDocumento())
                .endereco(cliente.getEndereco())
                .cep(cliente.getCep())
                .email(cliente.getEmail())
                .build();
    }
}
