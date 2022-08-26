package com.sge.util;

import com.sge.exceptions.InfoException;
import com.sge.model.entity.Categoria;
import org.springframework.http.HttpStatus;

public class UtilCategoria {
    public static Boolean validarCategoria(Categoria categoria) throws InfoException {
        if (categoria.getNome() == null || categoria.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
