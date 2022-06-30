package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Venda;
import com.sge.service.VendaService;
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
public class VendaController {
    public static final Logger logger = LoggerFactory.getLogger(VendaController.class);

    @Autowired
    private VendaService vendaService;

    @GetMapping(value = "/venda", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Venda>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(vendaService.findAll(pageable));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/venda/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venda> findVendaById(@PathVariable Long id) {
        Venda venda = vendaService.findById(id);
        return ResponseEntity.ok(venda);
    }

    @PostMapping(value = "/venda")
    public ResponseEntity<Venda> realizarVenda(@RequestBody Venda venda)
            throws URISyntaxException {
        try {
            Venda vendaRealizada = vendaService.salvarVenda(venda);
            return ResponseEntity.created(new URI("/api/venda" + vendaRealizada.getId())).body(venda);
        } catch (ResourceAlreadyExistsException | BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/venda/{id}")
    public ResponseEntity<Void> deleteVendaById(@PathVariable Long id) {
        try {
            vendaService.deleteById(id);
            logger.info("A venda " + id + " foi deletada");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "A venda " + id + " não foi encontrada");
        }
    }
}
