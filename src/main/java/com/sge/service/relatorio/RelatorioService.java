package com.sge.service.relatorio;

import com.sge.dto.RetornoRelatorioDTO;
import com.sge.exceptions.InfoException;

import java.text.ParseException;

public interface RelatorioService {
    RetornoRelatorioDTO vendasPorUsuario(Long id) throws InfoException;

    RetornoRelatorioDTO vendasFiltroPorData(String dataInicio, String dataFim) throws InfoException, ParseException;
}
