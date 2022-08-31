package com.sge.controller;

import com.sge.dto.ClienteDTO;
import com.sge.entity.Cliente;
import com.sge.exceptions.InfoException;
import com.sge.service.cliente.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@Tag(name = "Cliente", description = "API de Cliente")
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
