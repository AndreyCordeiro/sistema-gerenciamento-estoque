package com.sge.controller;

import com.sge.entity.Venda;
import com.sge.exceptions.InfoException;
import com.sge.service.venda.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venda")
public class VendaContoller {
    @Autowired
    private VendaService vendaService;

    @GetMapping("")
    public List<Venda> buscarTodos() {
        return vendaService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public Venda inserir(@RequestBody Venda venda) throws InfoException {
        return vendaService.inserir(venda);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        vendaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
