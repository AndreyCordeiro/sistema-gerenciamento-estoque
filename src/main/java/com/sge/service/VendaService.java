package com.sge.service;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Venda;
import com.sge.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.module.ResolutionException;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    private Boolean existsById(Long id) {
        return vendaRepository.existsById(id);
    }

    public Venda findById(Long id) {
        Venda venda = vendaRepository.findById(id).orElse(null);

        if (venda == null) {
            throw new ResolutionException("A venda " + id + " não foi encontrada");
        } else {
            return venda;
        }
    }

    public Page<Venda> findAll(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    public Venda salvarVenda(Venda venda) throws BadResourceException, ResourceAlreadyExistsException {
        if (venda != null) {
            return vendaRepository.save(venda);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao realizar venda");
            badResourceException.addErrorMessage("Venda está vazia ou é nula");
            throw badResourceException;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("A venda " + id + " não foi encontrada");
        } else {
            vendaRepository.deleteById(id);
        }
    }

    public Long count() {
        return vendaRepository.count();
    }
}