package com.sge.service.cargo;

import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Cargo;
import com.sge.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.module.ResolutionException;

@Service
public class CargoServiceImpl implements CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    public Boolean existsById(Long id) {
        return cargoRepository.existsById(id);
    }

    public Cargo findById(Long id) {
        Cargo cargo = cargoRepository.findById(id).orElse(null);

        if (cargo == null) {
            throw new ResolutionException("O cargo " + id + " não foi encontrado");
        } else {
            return cargo;
        }
    }

    public Page<Cargo> findAll(Pageable pageable) {
        return cargoRepository.findAll(pageable);
    }

    public Page<Cargo> findAllByNome(String nome, Pageable pageable) {
        return cargoRepository.findByNome(nome, pageable);
    }

    public Cargo saveCargo(Cargo cargo) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(cargo.getNome())) {
            if (cargo.getId() != null && existsById(cargo.getId())) {
                throw new ResourceAlreadyExistsException("O cargo " + cargo.getId() + " não foi encontrado");
            }
            return cargoRepository.save(cargo);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar cargo");
            badResourceException.addErrorMessage("Cargo está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void updateCargo(Cargo cargo) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(cargo.getNome())) {
            if (!existsById(cargo.getId())) {
                throw new ResourceNotFoundException("O cargo " + cargo.getId() + " não foi encontrado");
            }
            cargoRepository.save(cargo);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar cargo");
            badResourceException.addErrorMessage("Cargo está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("O cargo " + id + " não foi encontrado");
        } else {
            cargoRepository.deleteById(id);
        }
    }

    public Long count() {
        return cargoRepository.count();
    }
}
