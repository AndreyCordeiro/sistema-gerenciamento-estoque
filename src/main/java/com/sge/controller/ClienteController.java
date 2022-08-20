package com.sge.controller;

import com.sge.dto.ClienteDTO;
import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Pessoa;
import com.sge.service.cliente.ClienteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class ClienteController {

    public static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    @GetMapping(value = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClienteDTO>> findAll(@RequestBody(required = false) String nome, Pageable pageable) {
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(new ClienteDTO().convertCliente(clienteService.findAll(pageable)));
        } else {
            return ResponseEntity.ok(new ClienteDTO().convertCliente(clienteService.findAllByNome(nome, pageable)));
        }
    }

    @GetMapping(value = "/cliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Long id) {
        Pessoa pessoa = clienteService.findById(id);
        ClienteDTO clienteDTO = new ClienteDTO().convert(pessoa);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity<ClienteDTO> addCliente(@RequestBody Pessoa pessoa)
            throws URISyntaxException {
        try {
            Pessoa pessoaSalvo = clienteService.saveCliente(pessoa);
            return ResponseEntity.created(new URI("/api/cliente" + pessoaSalvo.getId())).body(new ClienteDTO().convert(pessoa));
        } catch (ResourceAlreadyExistsException | BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/cliente/{id}")
    public ResponseEntity<Pessoa> updateCliente(@RequestBody Pessoa pessoa, @PathVariable Long id) {
        try {
            pessoa.setId(id);
            clienteService.updateCliente(pessoa);
            logger.info("Cliente " + id + " atualizado");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error("Cliente " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/cliente/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
        try {
            clienteService.deleteById(id);
            logger.info("O cliente " + id + " foi deletado");
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "O cliente " + id + " não foi encontrado");
        }
    }
}
