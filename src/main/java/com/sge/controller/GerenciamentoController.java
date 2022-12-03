package com.sge.controller;

import com.sge.entity.Usuario;
import com.sge.security.JwtUtil;
import com.sge.service.gerenciamento.GerenciamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/gerenciamento")
@RequiredArgsConstructor
public class GerenciamentoController {
    @Autowired
    private GerenciamentoService gerenciamentoService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/senha-codigo")
    @CrossOrigin("http://localhost:3000")
    public String recuperarCodigo(@RequestBody Usuario usuario) {
        return gerenciamentoService.solicitarCodigo(usuario.getEmail());
    }

    @PostMapping("/senha-alterar")
    @CrossOrigin("http://localhost:3000")
    public String alterarSenha(@RequestBody Usuario usuario) {
        return gerenciamentoService.alterarSenha(usuario);
    }

    @PostMapping("/login")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario autenticado = (Usuario) authentication.getPrincipal();

        HashMap<String, Object> map = new HashMap<>();
        map.put("token", jwtUtil.gerarTokenUsername(autenticado));
        map.put("permissoes", autenticado.getAuthorities());

        return ResponseEntity.ok(map);
    }
}
