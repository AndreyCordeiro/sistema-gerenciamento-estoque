package com.sge.service;

import com.sge.model.entity.CargoFuncionario;
import com.sge.repository.CargoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoFuncionarioService {
    @Autowired
    private CargoFuncionarioRepository cargoFuncionarioRepository;

    protected CargoFuncionario salvarCargoFuncionario(CargoFuncionario cargoFuncionario) {
        return cargoFuncionarioRepository.save(cargoFuncionario);
    }
}