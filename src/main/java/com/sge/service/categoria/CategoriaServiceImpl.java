package com.sge.service.categoria;

import com.sge.exceptions.InfoException;
import com.sge.entity.Categoria;
import com.sge.repository.CategoriaRepository;
import com.sge.util.UtilCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria inserir(Categoria categoria) throws InfoException {
        if (UtilCategoria.validarCategoria(categoria)) {
            return categoriaRepository.save(categoria);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar categoria", HttpStatus.BAD_REQUEST);
        }
    }

    public Categoria alterar(Long id, Categoria categoria) throws InfoException {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoriaBuilder = Categoria.builder()
                    .id(id)
                    .nome(categoria.getNome() != null ? categoria.getNome() : null)
                    .build();

            if (UtilCategoria.validarCategoria(categoriaBuilder)) {
                categoriaRepository.save(categoriaBuilder);
            }
            return categoriaBuilder;
        } else {
            throw new InfoException("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            categoriaRepository.delete(categoria.get());
        } else {
            throw new InfoException("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
