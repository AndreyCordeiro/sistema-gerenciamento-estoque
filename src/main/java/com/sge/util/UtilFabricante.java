package com.sge.util;

import com.sge.dto.FabricanteDTO;
import com.sge.entity.Fabricante;
import com.sge.exceptions.InfoException;
import org.springframework.http.HttpStatus;

public class UtilFabricante {
    public static Boolean validarFabricante(Fabricante fabricante) throws InfoException {
        if (fabricante.getNome() == null || fabricante.getNome().equals("")) {
            throw new InfoException("MESSAGE.NOME_REQUIRED", HttpStatus.BAD_REQUEST);
        }
        return true;
    }

    public static FabricanteDTO converteFabricante(Fabricante fabricante) {
        return FabricanteDTO.builder()
                .nome(fabricante.getNome())
                .build();
    }
}
