package com.sge.controller;

import com.sge.dto.UsuarioDTO;
import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;
import com.sge.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "API de Usuário")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Buscar Usuários", description = "Busca todas os Usuários cadastrados")
    public List<UsuarioDTO> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Cadastrar Usuário", description = "Cadastra um Usuário")
    public UsuarioDTO inserir(@RequestBody Usuario usuario) throws InfoException {
        return usuarioService.inserir(usuario);
    }

    @PutMapping("/atualizar/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Alterar Usuário", description = "Altera um Usuário em específico")
    public Usuario alterar(@PathVariable("id") Long id, @RequestBody Usuario usuario) throws InfoException {
        return usuarioService.alterar(id, usuario);
    }

    @DeleteMapping("/deletar/{id}")
    @CrossOrigin("http://localhost:3000")
    @Operation(summary = "Deletar Usuário", description = "Exclui um Usuário em específico")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        usuarioService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
