package com.sge.util;

import com.sge.dto.CategoriaDTO;
import com.sge.entity.Categoria;
import com.sge.exceptions.InfoException;
import org.springframework.http.HttpStatus;

public class UtilCategoria {
    public static Boolean validarCategoria(Categoria categoria) throws InfoException {
        if (categoria.getNome() == null || categoria.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }

    public static CategoriaDTO converteCategoria(Categoria categoria) {
        return CategoriaDTO.builder()
                .nome(categoria.getNome())
                .build();
    }
}
