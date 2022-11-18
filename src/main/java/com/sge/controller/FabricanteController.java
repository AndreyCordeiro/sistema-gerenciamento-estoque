package com.sge.controller;

import com.sge.entity.Fabricante;
import com.sge.exceptions.InfoException;
import com.sge.service.fabricante.FabricanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabricante")
@RequiredArgsConstructor
@Tag(name = "Fabricante", description = "API de Fabricante")
public class FabricanteController {
    private final FabricanteService fabricanteService;

    @GetMapping("")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Buscar Fabricantes", description = "Busca todas os Fabricantes cadastrados")
    public List<Fabricante> buscarTodos() {
        return fabricanteService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Cadastrar Fabricante", description = "Cadastra um Fabricante")
    public Fabricante inserir(@RequestBody Fabricante fabricante) throws InfoException {
        return fabricanteService.inserir(fabricante);
    }

    @PutMapping("/atualizar/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Alterar Fabricante", description = "Altera um Fabricante em específico")
    public Fabricante alterar(@PathVariable("id") Long id, @RequestBody Fabricante fabricante) throws InfoException {
        return fabricanteService.alterar(id, fabricante);
    }

    @DeleteMapping("/deletar/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Deletar Fabricante", description = "Exclui um Fabricante em específico")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        fabricanteService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
