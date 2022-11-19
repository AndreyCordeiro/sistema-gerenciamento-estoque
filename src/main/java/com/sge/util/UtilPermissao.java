package com.sge.util;

import com.sge.entity.Permissao;
import com.sge.exceptions.InfoException;
import org.springframework.http.HttpStatus;

public class UtilPermissao {
    public static Boolean validarPermissao(Permissao permissao) throws InfoException {
        if (permissao.getNome() == null || permissao.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
