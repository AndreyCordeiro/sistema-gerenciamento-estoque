package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Categoria;
import com.sge.service.CategoriaService;
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

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class CategoriaController {
    public static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/categoria", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Categoria>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(categoriaService.findAll(pageable));
        } else {
            return ResponseEntity.ok(categoriaService.findAllByNome(nome, pageable));
        }
    }

    @GetMapping(value = "/categoria/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Long id) {
        Categoria produto = categoriaService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping(value = "/categoria")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria)
            throws URISyntaxException {
        try {
            Categoria categoriaSalva = categoriaService.saveCategoria(categoria);
            return ResponseEntity.created(new URI("/api/categoria" + categoriaSalva.getId())).body(categoria);
        } catch (ResourceAlreadyExistsException | BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/categoria/{id}")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        try {
            categoria.setId(id);
            categoriaService.updateCategoria(categoria);
            logger.info("Categoria " + id + " atualizada");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error("Categoria " + id + " não encontrada");
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/categoria/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
        try {
            categoriaService.deleteById(id);
            logger.info("A categoria " + id + " foi deletada");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "A categoria " + id + " não foi encontrada");
        }
    }
}
