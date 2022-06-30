package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Produto;
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
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ProdutoController {
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
    
	@PostMapping(value = "/produto")
	public ResponseEntity<Produto> addProduto(@RequestBody Produto produto)
			throws URISyntaxException {
		try {
			Produto novoProduto = produtoService.saveProduto(produto);
			return ResponseEntity.created(new URI("/api/produto" + novoProduto.getId())).body(produto);
		} catch (ResourceAlreadyExistsException | BadResourceException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/produto/{id}")
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, @PathVariable Long id) {
	
		try {
			produto.setId(id);
			produtoService.updateProduto(produto);
			logger.info("Produto " + id + " atualizado!");
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error("Produto " + id + " não encontrado!");
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(path="/produto/{id}")
	public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id) {
		try {
			produtoService.deleteById(id);
			logger.info("O produto " + id + " foi deletado!");
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "O produto " + id + " não foi encontrado!");
		}
	}
}
