package com.sge.util;

import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;
import org.springframework.http.HttpStatus;

public class UtilPessoa {
    public static Boolean validarPessoa(Usuario usuario) throws InfoException {
        if (usuario.getNome() == null || usuario.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (usuario.getDocumento() == null || usuario.getDocumento().equals("")) {
            throw new InfoException("MESSAGE.CPF_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (usuario.getEndereco() == null || usuario.getEndereco().equals("")) {
            throw new InfoException("MESSAGE.ENDERECO_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (usuario.getCep() == null || usuario.getCep().equals("")) {
            throw new InfoException("MESSAGE.CEP_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
            throw new InfoException("MESSAGE.EMAIL_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
            throw new InfoException("MESSAGE.SENHA_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
