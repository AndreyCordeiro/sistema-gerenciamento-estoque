package com.sge.util;

import com.sge.exceptions.InfoException;
import com.sge.entity.Marca;
import org.springframework.http.HttpStatus;

public class UtilMarca {
    public static Boolean validarMarca(Marca marca) throws InfoException {
        if (marca.getNome() == null || marca.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
