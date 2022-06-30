package com.sge.controller;

import com.sge.dto.FuncionarioDTO;
import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Funcionario;
import com.sge.service.FuncionarioService;
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
public class FuncionarioController {

    public static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(value = "/funcionario", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FuncionarioDTO>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(new FuncionarioDTO().convertFuncionario(funcionarioService.findAll(pageable)));
        } else {
            return ResponseEntity.ok(new FuncionarioDTO().convertFuncionario(funcionarioService.findAllByNome(nome, pageable)));
        }
    }

    @GetMapping(value = "/funcionario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Funcionario> findFuncionarioById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping(value = "/funcionario")
    public ResponseEntity<FuncionarioDTO> addFuncionario(@RequestBody Funcionario funcionario)
            throws URISyntaxException {
        try {
            Funcionario funcionarioSalvo = funcionarioService.saveFuncionario(funcionario);
            return ResponseEntity.created(new URI("/api/funcionario" + funcionarioSalvo.getId())).body(new FuncionarioDTO().convert(funcionario));
        } catch (ResourceAlreadyExistsException | BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/funcionario/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@RequestBody Funcionario funcionario, @PathVariable Long id) {
        try {
            funcionario.setId(id);
            funcionarioService.updateFuncionario(funcionario);
            logger.info("Funcionario " + id + " atualizado");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error("Funcionario " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/funcionario/{id}")
    public ResponseEntity<Void> deleteFuncionarioById(@PathVariable Long id) {
        try {
            funcionarioService.deleteById(id);
            logger.info("O funcionario " + id + " foi deletado");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "O funcionario " + id + " não foi encontrado");
        }
    }
}