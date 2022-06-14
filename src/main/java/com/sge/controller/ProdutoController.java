package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Produto;
import com.sge.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ProdutoController {
    //TODO correção
    public static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/produto", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Produto>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(produtoService.findAll(pageable));
        } else {
            return ResponseEntity.ok(produtoService.findAllByNome(nome, pageable));
        }
    }

    @GetMapping(value = "/produto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> findProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }
}