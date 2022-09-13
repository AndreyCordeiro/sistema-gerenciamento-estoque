package com.sge.controller;

import com.sge.dto.RetornoRelatorioDTO;
import com.sge.exceptions.InfoException;
import com.sge.service.relatorio.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorio")
@RequiredArgsConstructor
@Tag(name = "Relatório", description = "API de Relatório")
public class RelatorioController {
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Listar Vendas", description = "Lista as vendas realizadas por um usuário")
    public RetornoRelatorioDTO alterar(@PathVariable("id") Long id) throws InfoException {
        return relatorioService.vendasPorUsuario(id);
    }
}
