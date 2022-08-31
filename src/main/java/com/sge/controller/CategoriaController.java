package com.sge.controller;

import com.sge.exceptions.InfoException;
import com.sge.entity.Categoria;
import com.sge.service.categoria.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "API de Categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("")
    public List<Categoria> buscarTodos() {
        return categoriaService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public Categoria inserir(@RequestBody Categoria categoria) throws InfoException {
        return categoriaService.inserir(categoria);
    }

    @PutMapping("/atualizar/{id}")
    public Categoria alterar(@PathVariable("id") Long id, @RequestBody Categoria categoria) throws InfoException {
        return categoriaService.alterar(id, categoria);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        categoriaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
