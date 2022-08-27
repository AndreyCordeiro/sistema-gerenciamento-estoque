package com.sge.controller;

import com.sge.dto.ClienteDTO;
import com.sge.entity.Cliente;
import com.sge.exceptions.InfoException;
import com.sge.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public List<ClienteDTO> buscarTodos() {
        return clienteService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public ClienteDTO inserir(@RequestBody Cliente cliente) throws InfoException {
        return clienteService.inserir(cliente);
    }

    @PutMapping("/atualizar/{id}")
    public ClienteDTO alterar(@PathVariable("id") Long id, @RequestBody Cliente cliente) throws InfoException {
        return clienteService.alterar(id, cliente);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        clienteService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
