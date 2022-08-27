package com.sge.util;

import com.sge.entity.Pessoa;
import com.sge.exceptions.InfoException;
import org.springframework.http.HttpStatus;

public class UtilPessoa {
    public static Boolean validarPessoa(Pessoa pessoa) throws InfoException {
        if (pessoa.getNome() == null || pessoa.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (pessoa.getCpf() == null || pessoa.getCpf().equals("")) {
            throw new InfoException("MESSAGE.CPF_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (pessoa.getEndereco() == null || pessoa.getEndereco().equals("")) {
            throw new InfoException("MESSAGE.ENDERECO_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (pessoa.getCep() == null || pessoa.getCep().equals("")) {
            throw new InfoException("MESSAGE.CEP_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (pessoa.getEmail() == null || pessoa.getEmail().equals("")) {
            throw new InfoException("MESSAGE.EMAIL_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (pessoa.getSenha() == null || pessoa.getSenha().equals("")) {
            throw new InfoException("MESSAGE.SENHA_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
