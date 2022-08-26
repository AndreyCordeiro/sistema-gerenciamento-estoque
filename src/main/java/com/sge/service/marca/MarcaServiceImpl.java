package com.sge.service.marca;

import com.sge.exceptions.InfoException;
import com.sge.model.entity.Marca;
import com.sge.repository.MarcaRepository;
import com.sge.util.UtilMarca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> buscarTodos() {
        return marcaRepository.findAll();
    }

    public Marca inserir(Marca marca) throws InfoException {
        if (UtilMarca.validarMarca(marca)) {
            return marcaRepository.save(marca);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar marca", HttpStatus.BAD_REQUEST);
        }
    }

    public Marca alterar(Long id, Marca marca) throws InfoException {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isPresent()) {
            Marca marcaBuilder = Marca.builder()
                    .id(id)
                    .nome(marca.getNome())
                    .build();

            if (UtilMarca.validarMarca(marcaBuilder)) {
                marcaRepository.save(marcaBuilder);
            }
            return marcaBuilder;
        } else {
            throw new InfoException("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Marca> marca = marcaRepository.findById(id);

        if (marca.isPresent()) {
            marcaRepository.delete(marca.get());
        } else {
            throw new InfoException("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
