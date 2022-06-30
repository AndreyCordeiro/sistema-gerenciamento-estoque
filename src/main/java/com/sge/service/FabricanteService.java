package com.sge.service;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Fabricante;
import com.sge.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.module.ResolutionException;

@Service
public class FabricanteService {
    @Autowired
    private FabricanteRepository fabricanteRepository;

    private Boolean existsById(Long id) {
        return fabricanteRepository.existsById(id);
    }

    public Fabricante findById(Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id).orElse(null);

        if (fabricante == null) {
            throw new ResolutionException("O fabricante " + id + " não foi encontrado");
        } else {
            return fabricante;
        }
    }

    public Page<Fabricante> findAll(Pageable pageable) {
        return fabricanteRepository.findAll(pageable);
    }

    public Page<Fabricante> findAllByNome(String nome, Pageable pageable) {
        return fabricanteRepository.findByNome(nome, pageable);
    }

    public Fabricante saveFabricante(Fabricante fabricante) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(fabricante.getNome())) {
            if (fabricante.getId() != null && existsById(fabricante.getId())) {
                throw new ResourceAlreadyExistsException("O fabricante " + fabricante.getId() + " não foi encontrado");
            }
            return fabricanteRepository.save(fabricante);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o fabricante");
            badResourceException.addErrorMessage("Fabricante está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void updateFabricante(Fabricante fabricante) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(fabricante.getNome())) {
            if (!existsById(fabricante.getId())) {
                throw new ResourceNotFoundException("O fabricante " + fabricante.getId() + " não foi encontrado");
            }
            fabricanteRepository.save(fabricante);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o fabricante");
            badResourceException.addErrorMessage("Fabricante está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("O fabricante " + id + " não foi encontrado");
        } else {
            fabricanteRepository.deleteById(id);
        }
    }

    public Long count() {
        return fabricanteRepository.count();
    }
}
