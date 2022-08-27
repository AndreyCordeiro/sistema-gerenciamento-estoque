package com.sge.controller;

import com.sge.entity.Pessoa;
import com.sge.exceptions.InfoException;
import com.sge.service.pessoa.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("")
    public List<Pessoa> buscarTodos() {
        return pessoaService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public Pessoa inserir(@RequestBody Pessoa objeto) throws InfoException {
        return pessoaService.inserir(objeto);
    }

    @PutMapping("/atualizar/{id}")
    public Pessoa alterar(@PathVariable("id") Long id, @RequestBody Pessoa objeto) throws InfoException {
        return pessoaService.alterar(id, objeto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        pessoaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
