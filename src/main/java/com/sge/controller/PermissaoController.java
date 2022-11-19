package com.sge.controller;

import com.sge.entity.Permissao;
import com.sge.exceptions.InfoException;
import com.sge.service.permissao.PermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissao")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoService permissaoService;

    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public List<Permissao> buscarTodos() {
        return permissaoService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    @CrossOrigin("http://localhost:3000")
    public Permissao inserir(@RequestBody Permissao permissao) throws InfoException {
        return permissaoService.inserir(permissao);
    }

    @PutMapping("/atualizar/{id}")
    @CrossOrigin("http://localhost:3000")
    public Permissao alterar(@PathVariable("id") Long id, @RequestBody Permissao permissao) throws InfoException {
        return permissaoService.alterar(id, permissao);
    }

    @DeleteMapping("/deletar/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        permissaoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
