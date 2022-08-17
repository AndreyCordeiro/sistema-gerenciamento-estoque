package com.sge.service.cargoFuncionario;

import com.sge.model.entity.CargoFuncionario;
import com.sge.repository.CargoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoFuncionarioServiceImpl {
    @Autowired
    private CargoFuncionarioRepository cargoFuncionarioRepository;

    protected CargoFuncionario saveCargoFuncionario(CargoFuncionario cargoFuncionario) {
        return cargoFuncionarioRepository.save(cargoFuncionario);
    }
}