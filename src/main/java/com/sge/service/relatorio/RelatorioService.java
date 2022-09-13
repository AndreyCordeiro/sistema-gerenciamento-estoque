package com.sge.service.relatorio;

import com.sge.dto.RetornoRelatorioDTO;
import com.sge.exceptions.InfoException;

public interface RelatorioService {
    RetornoRelatorioDTO vendasPorUsuario(Long id) throws InfoException;
}
