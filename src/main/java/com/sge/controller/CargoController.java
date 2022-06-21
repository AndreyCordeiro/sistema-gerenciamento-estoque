package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Cargo;
import com.sge.service.CargoService;
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
public class CargoController {
    public static final Logger logger = LoggerFactory.getLogger(CargoController.class);
    @Autowired
    private CargoService cargoService;

    @GetMapping(value = "/cargo", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Cargo>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(cargoService.findAll(pageable));
        } else {
            return ResponseEntity.ok(cargoService.findAllByNome(nome, pageable));
        }
    }

    @GetMapping(value = "/cargo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cargo> findCargoById(@PathVariable Long id) {
        Cargo cargo = cargoService.findById(id);
        return ResponseEntity.ok(cargo);
    }

    @PostMapping(value = "/cargo")
    public ResponseEntity<Cargo> addCargo(@RequestBody Cargo cargo)
            throws URISyntaxException {
        try {
            Cargo novoCargo = cargoService.saveCargo(cargo);
            return ResponseEntity.created(new URI("/api/cargo" + novoCargo.getId())).body(cargo);
        } catch (ResourceAlreadyExistsException | BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/cargo/{id}")
    public ResponseEntity<Cargo> updateCargo(@RequestBody Cargo cargo, @PathVariable Long id) {

        try {
            cargo.setId(id);
            cargoService.updateCargo(cargo);
            logger.info("Cargo " + id + " atualizado!");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error("Cargo " + id + " não encontrado!");
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/cargo/{id}")
    public ResponseEntity<Void> deleteCargoById(@PathVariable Long id) {
        try {
            cargoService.deleteById(id);
            logger.info("O cargo " + id + " foi deletado!");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "O cargo " + id + " não foi encontrado!");
        }
    }
}
