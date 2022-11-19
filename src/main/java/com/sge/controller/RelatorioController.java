package com.sge.controller;

import com.sge.dto.RetornoRelatorioDTO;
import com.sge.exceptions.InfoException;
import com.sge.service.relatorio.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/relatorio")
@RequiredArgsConstructor
@Tag(name = "Relatório", description = "API de Relatório")
public class RelatorioController {
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/usuario/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Listar Vendas", description = "Lista as vendas realizadas por um usuário")
    public RetornoRelatorioDTO gerarRelatorioFuncionario(@PathVariable("id") Long id) throws InfoException {
        return relatorioService.vendasPorUsuario(id);
    }

    @GetMapping("/cliente/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Listar Compras", description = "Lista as compras realizadas por um cliente")
    public RetornoRelatorioDTO gerarRelatorioCliente(@PathVariable("id") Long id) throws InfoException {
        return relatorioService.comprasPorCliente(id);
    }

    @GetMapping
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Listar Vendas por data de início e data fim", description = "Lista as vendas realizadas em um intervalo de tempo")
    public RetornoRelatorioDTO listarVendasFiltroPorData(@RequestParam("dataInicio") String dataInicio,
                                                         @RequestParam("dataFim") String dataFim) throws InfoException, ParseException {
        return relatorioService.vendasFiltroPorData(dataInicio, dataFim);
    }
}
