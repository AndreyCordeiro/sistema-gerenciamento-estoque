package com.sge.service;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Categoria;
import com.sge.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.module.ResolutionException;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    private Boolean existsById(Long id) {
        return categoriaRepository.existsById(id);
    }

    public Categoria findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if (categoria == null) {
            throw new ResolutionException("A categoria " + id + " não foi encontrada");
        } else {
            return categoria;
        }
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public Page<Categoria> findAllByNome(String nome, Pageable pageable) {
        return categoriaRepository.findByNome(nome, pageable);
    }

    public Categoria saveCategoria(Categoria categoria) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(categoria.getNome())) {
            if (categoria.getId() != null && existsById(categoria.getId())) {
                throw new ResourceAlreadyExistsException("A categoria " + categoria.getId() + " não foi encontrada");
            }
            return categoriaRepository.save(categoria);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar a categoria");
            badResourceException.addErrorMessage("Categoria está vazia ou é nula");
            throw badResourceException;
        }
    }

    public void updateCategoria(Categoria categoria) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(categoria.getNome())) {
            if (!existsById(categoria.getId())) {
                throw new ResourceNotFoundException("A categoria " + categoria.getId() + " não foi encontrada");
            }
            categoriaRepository.save(categoria);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar a categoria");
            badResourceException.addErrorMessage("Categoria está vazia ou é nula");
            throw badResourceException;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("A categoria " + id + " não foi encontrada");
        } else {
            categoriaRepository.deleteById(id);
        }
    }

    public Long count() {
        return categoriaRepository.count();
    }
}
