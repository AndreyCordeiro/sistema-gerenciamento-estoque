package com.sge.controller;

import com.sge.dto.UsuarioDTO;
import com.sge.entity.Usuario;
import com.sge.exceptions.InfoException;
import com.sge.service.usuario.UsuarioService;
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
    public List<UsuarioDTO> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @PostMapping("/cadastrar")
    public UsuarioDTO inserir(@RequestBody Usuario usuario, @RequestParam String tipoUsuario) throws InfoException {
        return usuarioService.inserir(usuario, tipoUsuario);
    }

    @PutMapping("/atualizar/{id}")
    public UsuarioDTO alterar(@PathVariable("id") Long id, @RequestBody Usuario usuario, @RequestParam String tipoUsuario) throws InfoException {
        return usuarioService.alterar(id, usuario, tipoUsuario);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) throws InfoException {
        usuarioService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
