package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Fabricante;
import com.sge.service.FabricanteService;
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
public class FabricanteController {

    public static final Logger logger = LoggerFactory.getLogger(FabricanteController.class);

    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping(value = "/fabricante", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Fabricante>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(fabricanteService.findAll(pageable));
        } else {
            return ResponseEntity.ok(fabricanteService.findAllByNome(nome, pageable));
        }
    }

    @GetMapping(value = "/fabricante/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fabricante> findFabricanteById(@PathVariable Long id) {
        Fabricante fabricante = fabricanteService.findById(id);
        return ResponseEntity.ok(fabricante);
    }

    @PostMapping(value = "/fabricante")
    public ResponseEntity<Fabricante> addFabricante(@RequestBody Fabricante fabricante)
            throws URISyntaxException {
        try {
            Fabricante fabricanteSalvo = fabricanteService.saveFabricante(fabricante);
            return ResponseEntity.created(new URI("/api/fabricante" + fabricanteSalvo.getId())).body(fabricante);
        } catch (ResourceAlreadyExistsException | BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping(value = "/fabricante/{id}")
    public ResponseEntity<Fabricante> updateFabricante(@RequestBody Fabricante fabricante, @PathVariable Long id) {
        try {
            fabricante.setId(id);
            fabricanteService.updateFabricante(fabricante);
            logger.info("Fabricante " + id + " atualizado");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error("Fabricante " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping(path = "/fabricante/{id}")
    public ResponseEntity<Void> deleteFabricanteById(@PathVariable Long id) {
        try {
            fabricanteService.deleteById(id);
            logger.info("O fabricante " + id + " foi deletado");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "O fabricante " + id + " não foi encontrado");
        }
    }
}
