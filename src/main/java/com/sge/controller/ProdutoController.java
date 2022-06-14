package com.sge.controller;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Produto;
import com.sge.service.ProdutoService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

public class ProductController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/produtos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Produto>> findAll(
			@RequestBody(required=false) String nome, Pageable pageable) {
		
		if (StringUtils.isEmpty(nome)) {
			return ResponseEntity.ok(produtoService.findAll(pageable));
		}
		else {
			return ResponseEntity.ok(produtoService.findAllByNome(nome, pageable));
		}
	}
	
	@GetMapping(value = "/produto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> findProdutoById(@PathVariable long id) {
		
		try {
			Produto produto = produtoService.findById(id);
			return ResponseEntity.ok(produto);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
	
	@PostMapping(value = "/produtos")
	public ResponseEntity<Produto> addProduto(@RequestBody Produto produto)
			throws URISyntaxException {
				
		try {
			Produto novoProduto = produtoService.save(produto);
			return ResponseEntity.created(new URI("/api/produto" + novoProduto.getId())).body(produto);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/produto/{id}")
	public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto, @PathVariable long id) {
	
		try {
			produto.setId(id);
			produtoService.update(produto);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(path="/produto/{id}")
	public ResponseEntity<Void> deleteProdutoById(@PathVariable long id) {
		try {
			produtoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
