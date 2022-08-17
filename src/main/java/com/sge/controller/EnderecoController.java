package com.sge.controller;

import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Endereco;
import com.sge.service.endereco.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping(value = "/endereco/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> findEnderecoByCep(@PathVariable Long cep) throws ResourceNotFoundException {
        Endereco endereco = enderecoService.findByCep(cep);
        return ResponseEntity.ok(endereco);
    }
}