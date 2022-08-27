package com.sge.service.fabricante;

import com.sge.entity.Fabricante;
import com.sge.exceptions.InfoException;
import com.sge.repository.FabricanteRepository;
import com.sge.util.UtilFabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteServiceImpl implements FabricanteService {
    @Autowired
    private FabricanteRepository fabricanteRepository;

    public List<Fabricante> buscarTodos() {
        return fabricanteRepository.findAll();
    }

    public Fabricante inserir(Fabricante fabricante) throws InfoException {
        if (UtilFabricante.validarFabricante(fabricante)) {
            return fabricanteRepository.save(fabricante);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar marca", HttpStatus.BAD_REQUEST);
        }
    }

    public Fabricante alterar(Long id, Fabricante fabricante) throws InfoException {
        Optional<Fabricante> marcaOptional = fabricanteRepository.findById(id);

        if (marcaOptional.isPresent()) {
            Fabricante fabricanteBuilder = Fabricante.builder()
                    .id(id)
                    .nome(fabricante.getNome())
                    .build();

            if (UtilFabricante.validarFabricante(fabricanteBuilder)) {
                fabricanteRepository.save(fabricanteBuilder);
            }
            return fabricanteBuilder;
        } else {
            throw new InfoException("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Fabricante> marca = fabricanteRepository.findById(id);

        if (marca.isPresent()) {
            fabricanteRepository.delete(marca.get());
        } else {
            throw new InfoException("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
