package com.sge.controller;

import com.sge.exceptions.InfoException;
import com.sge.entity.Fabricante;
import com.sge.service.fabricante.FabricanteService;
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
    public List<Fabricante> buscarTodos() {
        return fabricanteService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public Fabricante inserir(@RequestBody Fabricante fabricante) throws InfoException {
        return fabricanteService.inserir(fabricante);
    }

    @PutMapping("/atualizar/{id}")
    public Fabricante alterar(@PathVariable("id") Long id, @RequestBody Fabricante fabricante) throws InfoException {
        return fabricanteService.alterar(id, fabricante);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        fabricanteService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
