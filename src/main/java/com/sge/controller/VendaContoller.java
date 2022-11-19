package com.sge.controller;

import com.sge.dto.VendaDTO;
import com.sge.entity.Venda;
import com.sge.exceptions.InfoException;
import com.sge.service.venda.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venda")
@RequiredArgsConstructor
@Tag(name = "Venda", description = "API de Venda")
public class VendaContoller {
    @Autowired
    private VendaService vendaService;

    @GetMapping("")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Buscar Vendas", description = "Busca todas as Vendas cadastradas")
    public List<VendaDTO> buscarTodos() {
        return vendaService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Cadastrar Venda", description = "Cadastra uma Venda")
    public VendaDTO inserir(@RequestBody Venda venda) throws InfoException {
        return vendaService.inserir(venda);
    }

    @DeleteMapping("/deletar/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Deletar Venda", description = "Exclui uma Venda em específico")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        vendaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
